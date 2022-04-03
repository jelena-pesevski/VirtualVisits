package org.unibl.etf.virtualvisits.models;

import lombok.*;
import org.unibl.etf.virtualvisits.models.entities.VirtualVisitEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuseumDetails extends Museum {
    private String address;
    private String phone;
    private String country;
    private String type;
    private Double longitude;
    private Double latitude;

}
