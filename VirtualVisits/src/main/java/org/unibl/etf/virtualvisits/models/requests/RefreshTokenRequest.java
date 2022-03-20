package org.unibl.etf.virtualvisits.models.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {

    private String refreshToken;

}
