package com.justforfun.deltazeta.repository;

import com.justforfun.deltazeta.model.tables.PointType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointTypeRepository extends JpaRepository<PointType, Integer> {

  Optional<PointType> findById(Integer id);

}
