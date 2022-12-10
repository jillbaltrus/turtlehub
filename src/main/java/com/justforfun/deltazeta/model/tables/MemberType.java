package com.justforfun.deltazeta.model.tables;

import static javax.persistence.GenerationType.IDENTITY;

import com.justforfun.deltazeta.model.enums.MemberTypeValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "member_types")
@Getter
public class MemberType {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(name = "name")
  @Enumerated(EnumType.STRING)
  private MemberTypeValue value;

}
