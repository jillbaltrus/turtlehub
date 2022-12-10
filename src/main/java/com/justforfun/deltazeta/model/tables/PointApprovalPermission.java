package com.justforfun.deltazeta.model.tables;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "point_approval_permission")
@Getter
public class PointApprovalPermission {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "point_type_id")
  private PointType pointType;

  @ManyToOne
  @JoinColumn(name = "position_id")
  private Position position;
}
