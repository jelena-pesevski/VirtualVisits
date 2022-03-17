package org.unibl.etf.virtualvisits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.requests.BuyTicketRequest;
import org.unibl.etf.virtualvisits.services.TicketService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public void buyTicket(@RequestBody @Valid BuyTicketRequest request) throws NotFoundException {
        ticketService.buyTicket(request);
    }
}
