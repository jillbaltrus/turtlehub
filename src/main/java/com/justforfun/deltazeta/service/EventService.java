package com.justforfun.deltazeta.service;

import static com.justforfun.deltazeta.model.enums.ResponseCode.BAD_REQUEST;
import static com.justforfun.deltazeta.model.enums.ResponseCode.OK;
import static com.justforfun.deltazeta.model.enums.ResponseCode.UNAUTHORIZED;

import com.justforfun.deltazeta.model.Response;
import com.justforfun.deltazeta.model.tables.Event;
import com.justforfun.deltazeta.model.tables.EventSignUp;
import com.justforfun.deltazeta.model.tables.Member;
import com.justforfun.deltazeta.model.tables.PointType;
import com.justforfun.deltazeta.repository.EventRepository;
import com.justforfun.deltazeta.repository.EventSignUpRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final EventSignUpRepository eventSignUpRepository;
  private final MemberService memberService;
  private final PointService pointService;

  @Autowired
  public EventService(EventRepository eventRepository, EventSignUpRepository eventSignUpRepository, MemberService memberService,
      PointService pointService) {
    this.eventRepository = eventRepository;
    this.eventSignUpRepository = eventSignUpRepository;
    this.memberService = memberService;
    this.pointService = pointService;
  }

  public List<Event> findAllEventsThisMonth() {
    return eventRepository.findAllEventsThisMonth();
  }

  public List<Event> findAllEventsForMonth(String date) {
    int month, year;
    try {
      month = LocalDate.parse(date).getMonthValue();
      year = LocalDate.parse(date).getYear();
    } catch (DateTimeParseException e) {
      return Collections.emptyList();
    }
    return eventRepository.findAllEventsByMonthAndYear(month, year);
  }

  public Optional<Event> findById(Integer id) {
    return eventRepository.findById(id);
  }

  public List<Member> findAllAttendeesForEvent(Integer eventId) {
    return eventSignUpRepository.findDistinctMembersByEvent(eventId);
  }

  public Response signUpForEvent(Integer eventId, Integer memberId) {
    Optional<Event> event = findById(eventId);
    Optional<Member> member = memberService.findById(memberId);
    if (event.isEmpty()) {
      return new Response(BAD_REQUEST, "Could not find event with id " + eventId);
    }
    if (member.isEmpty()) {
      return new Response(BAD_REQUEST, "Could not find member with id " + memberId);
    }
    EventSignUp eventSignUp = new EventSignUp(event.get(), member.get());
    eventSignUpRepository.save(eventSignUp);
    return new Response(OK, "Successfully signed user up for event ");
  }

  public Response removeSignUpForEvent(Integer eventId, Integer memberId) {
    eventSignUpRepository.removeSignUp(eventId, memberId);
    return new Response(OK, "Successfully deleted event sign-up");
  }

  public Response createNewEvent(Integer memberId, String eventName, String eventDescription, Integer pointTypeId,
      String time, Integer maximumAttendees) {
    boolean holdsPosition = memberService.findPositionNameByMemberId(memberId) == null;
    Optional<Member> member = memberService.findById(memberId);
    PointType pointType = null;
    if (pointTypeId != null) {
      Optional<PointType> optionalPointType = pointService.findPointTypeById(pointTypeId);
      if (optionalPointType.isEmpty()) {
        return new Response(BAD_REQUEST, "Could not find point type with id " + pointTypeId);
      }
      pointType = optionalPointType.get();
    }
    if (!holdsPosition) {
      return new Response(UNAUTHORIZED, "Only members that hold a position can create events!" + memberId);
    }
    if (member.isEmpty()) {
      return new Response(BAD_REQUEST, "Could not find member with id " + memberId);
    }
    if (eventName.isEmpty() || eventName.isBlank()) {
      return new Response(BAD_REQUEST, "Event name must not be empty.");
    }
    try {
      LocalDateTime.parse(time);
    } catch (DateTimeParseException e) {
      return new Response(BAD_REQUEST, e.getLocalizedMessage());
    }
    Event newEvent = new Event(eventName, eventDescription, pointType, member.get(), time, maximumAttendees);
    eventRepository.save(newEvent);
    return new Response(OK, "Successfully created new event " + eventName);
  }

  public Response deleteEvent(Integer memberId, Integer eventId) {
    Optional<Event> event = eventRepository.findById(eventId);
    if (event.isEmpty()) {
      return new Response(BAD_REQUEST, "That event does not exist.");
    }
    Integer createdByMemberId = eventRepository.findById(eventId).get().getCreatedByMember().getId();
    if (createdByMemberId != memberId) {
      return new Response(UNAUTHORIZED, "Only the member who created the event can delete it.");
    }
    eventSignUpRepository.deleteAllByEventId(eventId);
    eventRepository.deleteById(eventId);
    return new Response(OK, "Successfully deleted event.");
  }

  public List<Event> findDistinctEventsByMember(Integer memberId) {
    return eventSignUpRepository.findDistinctEventsByMember(memberId);
  }
}
