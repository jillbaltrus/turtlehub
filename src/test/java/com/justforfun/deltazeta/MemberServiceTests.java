package com.justforfun.deltazeta;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.justforfun.deltazeta.model.tables.Member;
import com.justforfun.deltazeta.service.MemberService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTests {

  @Autowired
  private MemberService memberService;

  @Test
  void testFindAllMembers() {
    List<Member> allMembers = memberService.findAll();

    assertNotNull(allMembers);
    assertThat(allMembers.size()).isGreaterThanOrEqualTo(1);
  }

  @Test
  void testFindByIdNonexistent() {
    Optional<Member> member = memberService.findById(-1);

    assertThat(member).isEmpty();
  }

  @Test
  void testFindById() {
    Integer id = 10;
    Optional<Member> member = memberService.findById(id);

    assertThat(member).isNotNull();
    assertThat(member.get().getId()).isEqualTo(id);
  }

}