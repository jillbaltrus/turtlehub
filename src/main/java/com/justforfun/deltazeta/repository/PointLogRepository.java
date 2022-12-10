package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.PointLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PointLogRepository extends JpaRepository<PointLog, Integer> {

  List<PointLog> findAllApprovedByMemberId(Integer id);

  List<PointLog> findAllRejectedByMemberId(Integer id);

  @Query("select pl from PointLog pl where pl.approved = false and pl.rejected = false")
  List<PointLog> findAllPendingByMemberId(Integer id);

  @Query(value = "select * from point_log pl join point_approval_permissions pap on pap.point_type_id = pl.point_type_id\n"
      + "join administration a on a.position_id = pap.position_id\n"
      + "where pl.approved = false and pl.rejected = false and a.member_id = :memberId", nativeQuery = true)
  List<PointLog> getAllPointsEligibileToApproveByMemberId(@Param("memberId") Integer memberId);

}
