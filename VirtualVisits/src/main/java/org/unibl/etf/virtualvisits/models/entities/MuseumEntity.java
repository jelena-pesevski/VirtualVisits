package org.unibl.etf.virtualvisits.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "museum")
public class MuseumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "museum_id", nullable = false)
    private Integer museumId;
    private String name;
    private String address;
    private String phone;
    private String city;
    private String country;
    private String type;
    private Double longitude;
    private Double latitude;

}
