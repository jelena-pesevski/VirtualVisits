package org.unibl.etf.virtualvisits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;

public interface LogEntityRepository extends JpaRepository<LogEntity, Integer> {
}
