package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.Administrator;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

  @Query("select p.name from Administrator a join Position p on a.position = p.id where a.member = :memberId")
  String findPositionNameByMember(@Param("memberId") Integer memberId);

}
