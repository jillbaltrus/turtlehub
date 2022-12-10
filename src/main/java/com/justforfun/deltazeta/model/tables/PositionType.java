package com.justforfun.deltazeta.model.tables;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position_types")
public class PositionType {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String value;

}