package com.justforfun.deltazeta.model.tables;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Getter
@NoArgsConstructor
public class Event {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "point_type_id")
  private PointType pointType;

  @Column(name = "time")
  private String time;

  @ManyToOne
  @JoinColumn(name = "created_by_member_id")
  private Member createdByMember;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "maximum_attendees")
  private Integer maximumAttendees;

  public Event(String name, String description, PointType pointType, Member createdByMember, String time, int maximumAttendees) {
    this.name = name;
    this.description = description;
    this.pointType = pointType;
    this.createdByMember = createdByMember;
    this.time = time;
    this.maximumAttendees = maximumAttendees;
  }
}
