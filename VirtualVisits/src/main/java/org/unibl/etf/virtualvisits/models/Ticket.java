package org.unibl.etf.virtualvisits.models;

import org.unibl.etf.virtualvisits.models.entities.VirtualVisitEntity;

import java.sql.Date;
import java.sql.Time;

public class Ticket {
    private String ticketNumber;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

}
