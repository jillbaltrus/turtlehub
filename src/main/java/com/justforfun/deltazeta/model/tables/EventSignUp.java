package com.justforfun.deltazeta.model.tables;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events_sign_up")
public class EventSignUp {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(name = "created_at")
  private String createdAt;

  public EventSignUp(Event event, Member member) {
    this.event = event;
    this.member = member;
  }
}
