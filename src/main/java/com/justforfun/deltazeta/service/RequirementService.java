package com.justforfun.deltazeta.service;

import com.justforfun.deltazeta.model.tables.Requirement;
import com.justforfun.deltazeta.repository.RequirementRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequirementService {

  private final RequirementRepository requirementRepository;

  @Autowired
  public RequirementService(RequirementRepository requirementRepository) {
    this.requirementRepository = requirementRepository;
  }

  public List<Requirement> findAll() {
    return requirementRepository.findAll();
  }
}
