package org.unibl.etf.virtualvisits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.virtualvisits.models.entities.UserEntity;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

   Optional<UserEntity> findByUsernameAndStatus(String username, UserStatus status);

   Boolean existsByUsername(String username);
}
