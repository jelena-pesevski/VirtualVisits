package org.unibl.etf.virtualvisits.models.responses;

import org.unibl.etf.virtualvisits.models.User;

public class LoginResponse extends User {
    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
