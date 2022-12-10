package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.Requirement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

  List<Requirement> findAll();

}
