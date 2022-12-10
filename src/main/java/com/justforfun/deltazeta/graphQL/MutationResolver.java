package com.justforfun.deltazeta.graphQL;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.justforfun.deltazeta.model.Response;
import com.justforfun.deltazeta.service.EventService;
import com.justforfun.deltazeta.service.MemberService;
import com.justforfun.deltazeta.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver  {

  private final PointService pointService;
  private final EventService eventService;

  @Autowired
  public MutationResolver(MemberService memberService, PointService pointService, EventService eventService) {
    this.pointService = pointService;
    this.eventService = eventService;
  }

  public Response approvePoint(Integer pointLogId, Integer memberId) {
    return pointService.approvePoint(pointLogId, memberId);
  }

  public Response rejectPoint(Integer pointLogId, Integer memberId) {
    return pointService.rejectPoint(pointLogId, memberId);
  }

  public Response submitNewPoint(Integer memberId, Integer pointTypeId, String description, int quantity) {
    return pointService.submitNewPoint(memberId, pointTypeId, description, quantity);
  }

  public Response signUpForEvent(Integer eventId, Integer memberId) {
    return eventService.signUpForEvent(eventId, memberId);
  }

  public Response removeSignUpForEvent(Integer eventId, Integer memberId) {
    return eventService.removeSignUpForEvent(eventId, memberId);
  }

  public Response createNewEvent(Integer memberId, String eventName, String eventDescription, Integer pointTypeId,
      String time, Integer maximumAttendees) {
    return eventService.createNewEvent(memberId, eventName, eventDescription, pointTypeId, time, maximumAttendees);
  }

  public Response deleteEvent(Integer memberId, Integer eventId) {
    return eventService.deleteEvent(memberId, eventId);
  }




}
