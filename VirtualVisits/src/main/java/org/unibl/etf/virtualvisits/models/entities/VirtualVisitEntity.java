package org.unibl.etf.virtualvisits.models.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "virtual_visit")
public class VirtualVisitEntity {
    private Integer virtualVisitId;
    private Date date;
    private Time start;
    private Time duration;
    private Double price;
    private String folder;
    private String ytLink;
    private MuseumEntity museum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "virtual_visit_id", nullable = false)
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "museum_id", referencedColumnName = "museum_id", nullable = false)
    public MuseumEntity getMuseum() {
        return museum;
    }

    public void setMuseum(MuseumEntity museum) {
        this.museum = museum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VirtualVisitEntity that = (VirtualVisitEntity) o;

        if (virtualVisitId != null ? !virtualVisitId.equals(that.virtualVisitId) : that.virtualVisitId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = virtualVisitId != null ? virtualVisitId.hashCode() : 0;
        return result;
    }

}
