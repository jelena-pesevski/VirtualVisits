package org.unibl.etf.virtualvisits.models.requests;

public class AttendVisitRequest {

    private Integer virtualVisitId;
    private String ticketNumber;

    public Integer getVirtualVisitId() {
        return virtualVisitId;
    }

    public void setVirtualVisitId(Integer virtualVisitId) {
        this.virtualVisitId = virtualVisitId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return "AttendVisitRequest{" +
                "virtualVisitId=" + virtualVisitId +
                ", ticketNumber='" + ticketNumber + '\'' +
                '}';
    }
}
