package org.unibl.etf.virtualvisits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;

import java.util.Optional;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Integer> {

    Optional<TicketEntity> findByVirtualVisitIdAndAndTicketNumber(Integer virtualVisitId, String ticketNumber);

    Optional<TicketEntity> findByVirtualVisitIdAndUserId(Integer virtualVisitId, Integer userId);

    int countByVirtualVisitId(Integer visitId);
}
