package org.unibl.etf.virtualvisits.models.responses;

import lombok.*;

import java.util.List;

@Data
public class AttendVisitResponse {
    private List<String> imagesUrls;
    private String videoUrl;
    private String ytLink;
    private long endingTimeInMillis;

}
