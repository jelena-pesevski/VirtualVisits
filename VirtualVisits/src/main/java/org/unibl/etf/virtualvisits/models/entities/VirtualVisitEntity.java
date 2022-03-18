package org.unibl.etf.virtualvisits.models.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "virtual_visit")
public class VirtualVisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "virtual_visit_id", nullable = false)
    private Integer virtualVisitId;
    private Date date;
    private Time start;
    private Time duration;
    private Double price;
    private String folder;
    private String ytLink;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "museum_id", referencedColumnName = "museum_id", nullable = false)
    private MuseumEntity museum;
}
