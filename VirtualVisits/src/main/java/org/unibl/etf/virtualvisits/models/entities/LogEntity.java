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
    @Basic
    @Column(name = "info", nullable = false, length = 90)
    private String info;
    @Basic
    @Column(name = "action", nullable = false, length = 45)
    private String action;
    @Basic
    @Column(name = "date_time", nullable = false)
    private Instant dateTime;
}
