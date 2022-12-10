package com.justforfun.deltazeta.graphQL;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.justforfun.deltazeta.model.MemberPointReport;
import com.justforfun.deltazeta.model.tables.Event;
import com.justforfun.deltazeta.model.tables.Member;
import com.justforfun.deltazeta.model.tables.PointLog;
import com.justforfun.deltazeta.service.EventService;
import com.justforfun.deltazeta.service.MemberService;
import com.justforfun.deltazeta.service.PointService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

  private final MemberService memberService;
  private final PointService pointService;
  private final EventService eventService;

  @Autowired
  public QueryResolver(MemberService memberService, PointService pointService, EventService eventService) {
    this.memberService = memberService;
    this.pointService = pointService;
    this.eventService = eventService;
  }

  public List<Member> getAllMembers() {
    return memberService.findAll();
  }

  public Optional<Member> getMemberById(Integer id) {
    return memberService.findById(id);
  }

  public String getPositionForMember(Integer id) {
    return memberService.findPositionNameByMemberId(id);
  }

  public MemberPointReport getPointReportForMember(Integer id) {
    return pointService.getPointReportForMemberId(id);
  }

  public List<PointLog> getAllApprovedPointsForMember(Integer id) {
    return pointService.findAllApprovedByMemberId(id);
  }

  public List<PointLog> getAllPendingPointsForMember(Integer id) {
    return pointService.findAllPendingByMemberId(id);
  }

  public List<PointLog> getAllRejectedPointsForMember(Integer id) {
    return pointService.findAllRejectedByMemberId(id);
  }

  public List<Event> getEventsForMember(Integer memberId) {
    return eventService.findDistinctEventsByMember(memberId);
  }

  public List<Member> getMembersForEvent(Integer eventId) {
    return eventService.findAllAttendeesForEvent(eventId);
  }

  public List<Event> getAllEventsThisMonth() {
    return eventService.findAllEventsThisMonth();
  }

  public List<Event> getAllEventsForMonth(String date) {
    return eventService.findAllEventsForMonth(date);
  }

}
