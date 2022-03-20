package org.unibl.etf.virtualvisits.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.unibl.etf.virtualvisits.models.JwtUser;
import org.unibl.etf.virtualvisits.models.enums.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Value("${authorization.token.header.name}")
    private String authorizationHeaderName;

    @Value("${authorization.token.header.prefix}")
    private String authorizationHeaderPrefix;

    @Value("${authorization.token.secret}")
    private String authorizationSecret;

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
            logger.debug("Token validation failed", ex);
        }
        filterChain.doFilter(request, response);
    }
}
