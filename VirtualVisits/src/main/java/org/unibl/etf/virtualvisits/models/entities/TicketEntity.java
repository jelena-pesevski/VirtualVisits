package org.unibl.etf.virtualvisits.models.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Integer ticketId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "virtual_visit_id", nullable = false)
    private Integer virtualVisitId;
    @Basic
    @Column(name = "ticket_number", nullable = false, length = 20)
    private String ticketNumber;

}
