package com.justforfun.deltazeta.model.tables;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "members")
@Getter
public class Member {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "age")
  private Integer age;

  @Column(name = "birthday")
  private String birthday;

  @Column(name = "major")
  private String major;

  @ManyToOne
  @JoinColumn(name = "grade_id")
  private Grade grade;

  @Column(name = "pledge_class")
  private String pledgeClass;

  @Column(name = "createdAt")
  private String createdAt;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private MemberStatus memberStatus;

  @ManyToOne
  @JoinColumn(name = "member_type_id")
  private MemberType memberType;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "pronouns")
  private String pronouns;

  @Column(name = "nuid")
  private String nuid;

  @Column(name = "expected_grad_date")
  private String expectedGradDate;

  @Column(name = "num_years_at_nu")
  private Integer numYearsAtNu;

  @Column(name = "fav_candy")
  private String favCandy;

  @Column(name = "fav_color")
  private String favColor;

  @Column(name = "fav_restaurant")
  private String favRestaurant;

  @Column(name = "fav_store")
  private String favStore;

  @Column(name = "hobbies")
  private String hobbies;

}
