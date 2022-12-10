package com.justforfun.deltazeta.model;

import static com.justforfun.deltazeta.model.enums.MemberStatusValue.CO_OP;
import static com.justforfun.deltazeta.model.enums.PointTypeValue.ACADEMIC;

import com.justforfun.deltazeta.model.enums.PointTypeValue;
import com.justforfun.deltazeta.model.tables.Member;
import com.justforfun.deltazeta.model.tables.PointLog;
import com.justforfun.deltazeta.model.tables.Requirement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberPointReport {

  private Member member;
  private List<PointReport> pointReports;
  private boolean allPointsEarned;

  public MemberPointReport(Member member, List<PointLog> allPoints, List<Requirement> requirements) {
    this.member = member;
    this.pointReports = this.makePointReports(member, allPoints, requirements);
    this.allPointsEarned = this.pointReports.stream().allMatch(pr -> pr.getRequired() <= pr.getEarned());
  }

  private List<PointReport> makePointReports(Member member, List<PointLog> allPoints, List<Requirement> requirements) {
    List<PointReport> pointReports = new ArrayList<>();
    boolean isMemberOnCoOp = member.getMemberStatus().getValue().equals(CO_OP);
    for (PointTypeValue pointType : PointTypeValue.values()) {
      List<Requirement> filteredRequirements = requirements.stream().filter(r -> r.getPointType().getValue().equals(pointType)
          && r.getMemberType().getValue().equals(member.getMemberType().getValue())).collect(Collectors.toList());
      if (filteredRequirements.isEmpty()) {
        continue;
      }
      Requirement requirement = filteredRequirements.get(0);
      List<PointLog> pointsForPointType = allPoints.stream().filter(p -> p.getPointType().getValue().equals(pointType)).collect(Collectors.toList());
      if (pointType == ACADEMIC) {
        pointReports.add(new PointReport(pointType, pointsForPointType.size(), isMemberOnCoOp ? 0 : requirement.getNumRequired()));
      } else {
        pointReports.add(new PointReport(pointType, pointsForPointType.size(), requirement.getNumRequired()));
      }
    }
    return pointReports;
  }
}
