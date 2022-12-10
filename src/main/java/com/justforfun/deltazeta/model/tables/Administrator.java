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
@Table(name = "administration")
public class Administrator {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "position")
  private Position position;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(name = "year")
  private Integer year;

  @Column(name = "created_at")
  private String createdAt;

}
