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

@Entity
@Table(name = "requirements")
@Getter
public class Requirement {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "point_type_id")
  private PointType pointType;

  @Column(name = "num_required")
  private Integer numRequired;

  @ManyToOne
  @JoinColumn(name = "member_type_id")
  private MemberType memberType;
}
