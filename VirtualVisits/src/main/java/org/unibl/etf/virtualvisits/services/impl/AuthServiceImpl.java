package org.unibl.etf.virtualvisits.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.UnauthorizedException;
import org.unibl.etf.virtualvisits.models.JwtUser;
import org.unibl.etf.virtualvisits.models.responses.LoginResponse;
import org.unibl.etf.virtualvisits.models.responses.RefreshTokenResponse;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.models.requests.LoginRequest;
import org.unibl.etf.virtualvisits.models.requests.RefreshTokenRequest;
import org.unibl.etf.virtualvisits.services.AuthService;
import org.unibl.etf.virtualvisits.services.UserService;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserService service;

    private final ModelMapper modelMapper;

    @Value("${authorization.token.secret}")
    private String tokenSecret;

    @Value("${authorization.refresh.token.secret}")
    private String refreshTokenSecret;

    @Value("${authorization.token.expiration-time}")
    private String tokenExpirationTime;

    @Value("${authorization.refresh.token.expiration-time}")
    private String refreshTokenExpirationTime;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService service, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.modelMapper=modelMapper;
    }

    @Override
    public LoginResponse login(LoginRequest request) throws UnauthorizedException {
        LoginResponse response=null;
        try{
            Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            JwtUser user=(JwtUser) authenticate.getPrincipal();
            User userObject=service.findById(user.getId());

            //set otpToken
            String otpToken=String.valueOf(System.currentTimeMillis());
            userObject.setOtpToken(otpToken);
            service.update(userObject.getUserId(), userObject);

            response=modelMapper.map(userObject, LoginResponse.class);
            response.setToken(generateJwt(user));
            response.setRefreshToken(generateRefreshToken(user));

        }catch(Exception e){
            throw new UnauthorizedException();
        }
        return response;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws UnauthorizedException {
        RefreshTokenResponse refreshTokenResponse=null;
        try{
            Claims claims= Jwts.parser()
                    .setSigningKey(refreshTokenSecret)
                    .parseClaimsJws(refreshTokenRequest.getRefreshToken())
                    .getBody();
            JwtUser user=new JwtUser(Integer.valueOf(claims.getId()), claims.getSubject(), null, Role.valueOf(claims.get("role", String.class)));
            refreshTokenResponse=new RefreshTokenResponse();
            refreshTokenResponse.setToken(generateJwt(user));
        }catch(Exception e){
            throw new UnauthorizedException();
        }
        return refreshTokenResponse;
    }

    private String generateJwt(JwtUser user){
        return Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setExpiration(new Date(System.currentTimeMillis()+ Long.parseLong(tokenExpirationTime)))
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    private String generateRefreshToken(JwtUser user){
        return Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .claim("role", user.getRole().toString())
                .setExpiration(new Date(System.currentTimeMillis()+ Long.parseLong(refreshTokenExpirationTime)))
                .signWith(SignatureAlgorithm.HS512, refreshTokenSecret)
                .compact();
    }

}
