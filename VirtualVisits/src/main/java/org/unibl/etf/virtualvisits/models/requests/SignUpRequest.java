package org.unibl.etf.virtualvisits.models.requests;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    @Email
    private String mail;
    @NotBlank
    @Size(min = 12)
    @Pattern(regexp = "^[^@#/]+$")
    private String username;
    @NotBlank
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{15,}$" )
    private String password;

}
