package org.unibl.etf.virtualvisits.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "museum")
public class MuseumEntity {
    private Integer museumId;
    private String name;
    private String address;
    private String phone;
    private String city;
    private String country;
    private String type;
    private Double longitude;
    private Double latitude;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "museum_id", nullable = false)
    public Integer getMuseumId() {
        return museumId;
    }

    public void setMuseumId(Integer museumId) {
        this.museumId = museumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MuseumEntity that = (MuseumEntity) o;

        if (museumId != null ? !museumId.equals(that.museumId) : that.museumId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = museumId != null ? museumId.hashCode() : 0;
        return result;
    }

}
