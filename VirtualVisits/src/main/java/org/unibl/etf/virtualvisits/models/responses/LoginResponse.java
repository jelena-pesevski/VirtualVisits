package org.unibl.etf.virtualvisits.models.responses;

import lombok.*;
import org.unibl.etf.virtualvisits.models.User;

@Data
public class LoginResponse extends User {
    private String token;
    private String refreshToken;

}
