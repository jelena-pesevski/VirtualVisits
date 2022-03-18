package org.unibl.etf.virtualvisits.models;

import lombok.*;
import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;

@Data
public class User {
    private Integer userId;
    private String firstname;
    private String lastname;
    private String mail;
    private String username;
    private Role role;
    private UserStatus status;
    private String otpToken;

}
