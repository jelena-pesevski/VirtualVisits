package org.unibl.etf.virtualvisits.models.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Integer logId;
    private String info;
    private String action;
    @Basic
    @Column(name = "date_time", nullable = false)
    private Instant dateTime;
    private String username;
}
