package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Ticket;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;
import org.unibl.etf.virtualvisits.models.requests.BuyTicketRequest;

public interface TicketService {

    void buyTicket(BuyTicketRequest request) throws NotFoundException;

    TicketEntity findByVirtualVisitIdAndUserId(Integer virtualVisitId, Integer userId) throws NotFoundException;
}
