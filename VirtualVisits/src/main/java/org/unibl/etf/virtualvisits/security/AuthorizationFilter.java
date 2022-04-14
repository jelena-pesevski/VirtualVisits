package org.unibl.etf.virtualvisits.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.unibl.etf.virtualvisits.models.JwtUser;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;
import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.services.LogService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final LogService logService;

    @Value("${authorization.token.header.name}")
    private String authorizationHeaderName;

    @Value("${authorization.token.header.prefix}")
    private String authorizationHeaderPrefix;

    @Value("${authorization.token.secret}")
    private String authorizationSecret;

    public AuthorizationFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader=request.getHeader(authorizationHeaderName);
        String requestURL = request.getRequestURL().toString();

        if(authorizationHeader==null || !authorizationHeader.startsWith(authorizationHeaderPrefix)){
            filterChain.doFilter(request, response);
            return;
        }
        String token=authorizationHeader.replace(authorizationHeaderPrefix, "");
        try{
            Claims claims= Jwts.parser()
                    .setSigningKey(authorizationSecret)
                    .parseClaimsJws(token)
                    .getBody();
            JwtUser jwtUser=new JwtUser(Integer.valueOf(claims.getId()), claims.getSubject(), null, Role.valueOf(claims.get("role", String.class)));
            Authentication authentication=new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception ex){
            logService.insert(new LogEntity(0, "Jwt token validation failed." , "TOKEN-VALIDATION-FAIL", Instant.now(), null));
            logger.debug("Token validation failed", ex);
        }
        filterChain.doFilter(request, response);
    }
}
