package org.unibl.etf.virtualvisits.models.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Integer logId;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "action", nullable = false, length = 45)
    private String action;
    @Basic
    @Column(name = "datetime", nullable = false)
    private Timestamp dateTime;

}
