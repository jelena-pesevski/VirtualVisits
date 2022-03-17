package org.unibl.etf.virtualvisits.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class TicketEntity {
    private Integer ticketId;
    private Integer userId;
    private Integer virtualVisitId;
    private String ticketNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "virtual_visit_id", nullable = false)
    public Integer getVirtualVisitId() {
        return virtualVisitId;
    }

    public void setVirtualVisitId(Integer virtualVisitId) {
        this.virtualVisitId = virtualVisitId;
    }

    @Basic
    @Column(name = "ticket_number", nullable = false, length = 20)
    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        return result;
    }
}
