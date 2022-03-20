package org.unibl.etf.virtualvisits.models.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendVisitRequest {

    private Integer virtualVisitId;
    private String ticketNumber;

}
