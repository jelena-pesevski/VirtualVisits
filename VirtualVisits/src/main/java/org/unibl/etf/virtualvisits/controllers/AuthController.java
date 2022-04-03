package org.unibl.etf.virtualvisits.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualvisits.exceptions.ConflictException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.exceptions.UnauthorizedException;
import org.unibl.etf.virtualvisits.models.responses.LoginResponse;
import org.unibl.etf.virtualvisits.models.responses.RefreshTokenResponse;
import org.unibl.etf.virtualvisits.models.requests.LoginRequest;
import org.unibl.etf.virtualvisits.models.requests.RefreshTokenRequest;
import org.unibl.etf.virtualvisits.models.requests.SignUpRequest;
import org.unibl.etf.virtualvisits.services.AuthService;
import org.unibl.etf.virtualvisits.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) throws UnauthorizedException {
        return authService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws ConflictException {
        userService.singUp(signUpRequest);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) throws UnauthorizedException {
        return authService.refreshToken(refreshTokenRequest);
    }

    @GetMapping("/logout")
    public boolean logout(@RequestHeader(value = "userId") Integer userId) throws NotFoundException {
        return authService.logout(userId);
    }
}
