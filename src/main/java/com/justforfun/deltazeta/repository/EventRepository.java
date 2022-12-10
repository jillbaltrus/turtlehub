package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Integer> {

  @Query(value = "select * from events where MONTH(events.time) = MONTH(current_date) and YEAR(events.time) = YEAR(current_date)", nativeQuery = true)
  List<Event> findAllEventsThisMonth();

  @Query(value = "select * from events where MONTH(events.time) = month and YEAR(events.time) = year", nativeQuery = true)

  List<Event> findAllEventsByMonthAndYear(int month, int year);

  @Modifying
  void deleteById(Integer id);

}
