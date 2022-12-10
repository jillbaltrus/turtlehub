package com.justforfun.deltazeta.service;

import static com.justforfun.deltazeta.model.enums.ResponseCode.BAD_REQUEST;
import static com.justforfun.deltazeta.model.enums.ResponseCode.OK;

import com.justforfun.deltazeta.model.MemberPointReport;
import com.justforfun.deltazeta.model.Response;
import com.justforfun.deltazeta.model.tables.Member;
import com.justforfun.deltazeta.model.tables.PointLog;
import com.justforfun.deltazeta.model.tables.PointType;
import com.justforfun.deltazeta.model.tables.Requirement;
import com.justforfun.deltazeta.repository.PointLogRepository;
import com.justforfun.deltazeta.repository.PointTypeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

  private final PointLogRepository pointLogRepository;
  private final PointTypeRepository pointTypeRepository;
  private final MemberService memberService;
  private final RequirementService requirementService;

  @Autowired
  public PointService(PointLogRepository pointLogRepository, PointTypeRepository pointTypeRepository, MemberService memberService, RequirementService requirementService) {
    this.pointLogRepository = pointLogRepository;
    this.pointTypeRepository = pointTypeRepository;
    this.memberService = memberService;
    this.requirementService = requirementService;
  }

  public MemberPointReport getPointReportForMemberId(Integer id) {
    Optional<Member> member = memberService.findById(id);
    if (member.isEmpty()) {
      return null;
    }
    List<Requirement> requirements = requirementService.findAll();
    List<PointLog> points = findAllApprovedByMemberId(id);
    return new MemberPointReport(member.get(), points, requirements);
  }

  public List<PointLog> findAllApprovedByMemberId(Integer id) {
    return pointLogRepository.findAllApprovedByMemberId(id);
  }

  public List<PointLog> findAllPendingByMemberId(Integer id) {
    return pointLogRepository.findAllPendingByMemberId(id);
  }

  public List<PointLog> findAllRejectedByMemberId(Integer id) {
    return pointLogRepository.findAllRejectedByMemberId(id);
  }

  public Response approvePoint(Integer pointLogId, Integer memberId) {
    Optional<Response> optionalResponse = checkMemberAndPointExist(memberId, pointLogId);
    if (optionalResponse.isPresent()) {
      return optionalResponse.get();
    }
    PointLog pointLogToApprove = pointLogRepository.findById(pointLogId).get();
    pointLogToApprove.setApproved(true);
    pointLogToApprove.setRejected(false);
    pointLogToApprove.setApprovedByMember(memberService.findById(memberId).get());
    pointLogToApprove.setUpdatedAt(LocalDateTime.now().toString());
    pointLogRepository.save(pointLogToApprove);
    return new Response(OK, "Successfully approved point");
  }

  public Response rejectPoint(Integer pointLogId, Integer memberId) {
    Optional<Response> optionalResponse = checkMemberAndPointExist(memberId, pointLogId);
    if (optionalResponse.isPresent()) {
      return optionalResponse.get();
    }
    PointLog pointLogToReject = pointLogRepository.findById(pointLogId).get();
    pointLogToReject.setApproved(false);
    pointLogToReject.setRejected(true);
    pointLogToReject.setRejectedByMember(memberService.findById(memberId).get());
    pointLogToReject.setUpdatedAt(LocalDateTime.now().toString());
    pointLogRepository.save(pointLogToReject);
    return new Response(OK, "Successfully rejected point");
  }

  private Optional<Response> checkMemberAndPointExist(Integer memberId, Integer pointLogId) {
    Optional<PointLog> point = pointLogRepository.findById(pointLogId);
    Optional<Member> approvedByMember = memberService.findById(memberId);
    if (point.isEmpty()) {
      return Optional.of(new Response(BAD_REQUEST, "Could not find point to delete."));
    }
    if (approvedByMember.isEmpty()) {
      return Optional.of(new Response(BAD_REQUEST, "Could not find member with ID " + memberId));
    }
    return Optional.empty();
  }

  public Response submitNewPoint(Integer memberId, Integer pointTypeId, String description, int quantity) {
    Optional<PointType> pointType = findPointTypeById(pointTypeId);
    Optional<Member> member = memberService.findById(memberId);
    if (member.isEmpty()) {
      return new Response(BAD_REQUEST, "Could not find member with ID " + memberId);
    }
    if (pointType.isEmpty()) {
      return new Response(BAD_REQUEST, "Could not find point type with ID " + memberId);
    }
    PointLog pointLog = new PointLog(pointType.get(), member.get(), description, quantity);
    pointLogRepository.save(pointLog);
    return new Response(OK, "Successfully submitted point request");

  }

  public Optional<PointType> findPointTypeById(Integer pointTypeId) {
    return pointTypeRepository.findById(pointTypeId);
  }
}
