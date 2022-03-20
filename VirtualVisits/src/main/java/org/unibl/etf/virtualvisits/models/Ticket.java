package org.unibl.etf.virtualvisits.models;

import lombok.*;
import org.unibl.etf.virtualvisits.models.entities.VirtualVisitEntity;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String ticketNumber;

}
