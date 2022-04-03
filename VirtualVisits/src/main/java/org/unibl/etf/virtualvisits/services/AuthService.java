package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.exceptions.UnauthorizedException;
import org.unibl.etf.virtualvisits.models.responses.LoginResponse;
import org.unibl.etf.virtualvisits.models.responses.RefreshTokenResponse;
import org.unibl.etf.virtualvisits.models.requests.LoginRequest;
import org.unibl.etf.virtualvisits.models.requests.RefreshTokenRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request) throws UnauthorizedException;

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws UnauthorizedException;

    boolean logout(Integer userId) throws NotFoundException;
}
