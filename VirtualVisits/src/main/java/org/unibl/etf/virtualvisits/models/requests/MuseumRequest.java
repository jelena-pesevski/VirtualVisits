package org.unibl.etf.virtualvisits.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MuseumRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private String country;
    @NotBlank
    private String type;
    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
}
