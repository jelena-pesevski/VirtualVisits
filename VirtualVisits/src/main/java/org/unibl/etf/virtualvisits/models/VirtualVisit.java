package org.unibl.etf.virtualvisits.models;

import java.sql.Date;
import java.sql.Time;

public class VirtualVisit {

    private Integer virtualVisitId;
    private Date date;
    private Time start;
    private Time duration;
    private Double price;
    private String folder;
    private String ytLink;
    private String museumId;
    private String museumName;

    public Integer getVirtualVisitId() {
        return virtualVisitId;
    }

    public void setVirtualVisitId(Integer virtualVisitId) {
        this.virtualVisitId = virtualVisitId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getYtLink() {
        return ytLink;
    }

    public void setYtLink(String ytLink) {
        this.ytLink = ytLink;
    }

    public String getMuseumId() {
        return museumId;
    }

    public void setMuseumId(String museumId) {
        this.museumId = museumId;
    }

    public String getMuseumName() {
        return museumName;
    }

    public void setMuseumName(String museumName) {
        this.museumName = museumName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
