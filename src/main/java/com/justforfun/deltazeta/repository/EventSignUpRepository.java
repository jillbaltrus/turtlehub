package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.Event;
import com.justforfun.deltazeta.model.tables.EventSignUp;
import com.justforfun.deltazeta.model.tables.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSignUpRepository extends JpaRepository<EventSignUp, Integer> {

  List<Member> findDistinctMembersByEvent(Integer eventId);

  @Modifying
  @Query("delete from EventSignUp esu where esu.event=:eventId and esu.member=:memberId")
  void removeSignUp(@Param("eventId") Integer eventId, @Param("memberId") Integer memberId);

  List<Event> findDistinctEventsByMember(Integer memberId);

  @Modifying
  void deleteAllByEventId(Integer eventId);

}
