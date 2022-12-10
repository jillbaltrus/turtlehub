package com.justforfun.deltazeta.service;

import com.justforfun.deltazeta.model.tables.Member;
import com.justforfun.deltazeta.repository.AdministratorRepository;
import com.justforfun.deltazeta.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final AdministratorRepository administratorRepository;


  @Autowired
  public MemberService(MemberRepository memberRepository, AdministratorRepository administratorRepository) {
    this.memberRepository = memberRepository;
    this.administratorRepository = administratorRepository;
  }

  public List<Member> findAll() {
    return this.memberRepository.findAll();
  }

  public Optional<Member> findById(Integer id) {
    return this.memberRepository.findById(id);
  }

  public String findPositionNameByMemberId(Integer id) {
    return administratorRepository.findPositionNameByMember(id);
  }
}
