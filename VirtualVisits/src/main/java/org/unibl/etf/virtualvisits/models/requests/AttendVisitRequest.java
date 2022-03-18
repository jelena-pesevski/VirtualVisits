package org.unibl.etf.virtualvisits.models.requests;

import lombok.*;

@Data
public class AttendVisitRequest {

    private Integer virtualVisitId;
    private String ticketNumber;

}
