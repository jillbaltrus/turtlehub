package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

  List<Member> findAll();

}
