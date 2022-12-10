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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "point_log")
@Getter
@Setter
@NoArgsConstructor
public class PointLog {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "pointTypeId")
  private PointType pointType;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "approved_by_member_id")
  private Member approvedByMember;

  @ManyToOne
  @JoinColumn(name = "rejected_by_member_id")
  private Member rejectedByMember;

  @Column(name = "description")
  private String description;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "updated_at")
  private String updatedAt;

  @Column(name = "approved")
  private Boolean approved;

  @Column(name = "rejected")
  private Boolean rejected;

  public PointLog(PointType pointType, Member member, String description, int quantity) {
    this.pointType = pointType;
    this.member = member;
    this.description = description;
    this.quantity = quantity;
    this.approved = false;
    this.rejected = false;
  }

}
