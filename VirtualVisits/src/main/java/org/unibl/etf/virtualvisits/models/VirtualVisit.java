package org.unibl.etf.virtualvisits.models;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Data
public class VirtualVisit {

    private Integer virtualVisitId;
    private Date date;
    private Time start;
    private Time duration;
    private Double price;
    private String folder;
    private String ytLink;
    private String museumId;
    private String museumName;

}
