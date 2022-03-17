package org.unibl.etf.virtualbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.virtualbank.models.entities.TransactionEntity;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Integer> {
}
