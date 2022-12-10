package com.justforfun.deltazeta;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.justforfun.deltazeta.model.MemberPointReport;
import com.justforfun.deltazeta.model.PointReport;
import com.justforfun.deltazeta.model.tables.PointLog;
import com.justforfun.deltazeta.service.PointService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointServiceTests {

  @Autowired
  private PointService pointService;

  @Test
  void testGetPointReportForMemberId() {
    Integer id = 1;

    MemberPointReport report = pointService.getPointReportForMemberId(id);
    List<PointLog> allApprovedPoints = pointService.findAllApprovedByMemberId(id);
    int totalPointsInReport = report.getPointReports().stream().map(PointReport::getEarned).reduce(0,Integer::sum);

    assertThat(report.isAllPointsEarned()).isFalse();
    assertThat(report.getMember().getId()).isEqualTo(id);
    assertThat(totalPointsInReport).isEqualTo(allApprovedPoints.size());
  }

}
