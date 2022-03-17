package org.unibl.etf.virtualbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.virtualbank.models.entities.AccountEntity;

import java.util.Optional;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByCreditCardNumber(String creditCardNumber);
}
