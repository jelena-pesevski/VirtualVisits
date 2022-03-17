package org.unibl.etf.virtualvisits.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.unibl.etf.virtualvisits.security.models.AuthorizationRules;
import org.unibl.etf.virtualvisits.security.models.Rule;
import org.unibl.etf.virtualvisits.services.JwtUserDetailsService;
import org.unibl.etf.virtualvisits.utils.CustomPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthorizationFilter authorizationFilter;

    private final JwtUserDetailsService jwtUserDetailsService;

    public WebSecurityConfig(AuthorizationFilter authorizationFilter, JwtUserDetailsService jwtUserDetailsService) {
        this.authorizationFilter = authorizationFilter;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomPasswordEncoder passwordEncoder(){
        return new CustomPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http=http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http=createAuthorizationRules(http);

        //xss protection because of jwt being stored in storage on client side
        http.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");
        //adding custom filter for authorization
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //method to define which paths are secured depending on user role
    private HttpSecurity createAuthorizationRules(HttpSecurity http) throws Exception{
        AuthorizationRules authorizationRules=new ObjectMapper().readValue(new ClassPathResource("rules.json").getInputStream(), AuthorizationRules.class);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry interceptor=http.authorizeRequests();
        interceptor=interceptor.antMatchers(HttpMethod.POST, "/login").permitAll().antMatchers(HttpMethod.POST, "/sign-up").permitAll().antMatchers(HttpMethod.POST, "/refresh-token").permitAll()
                .antMatchers(HttpMethod.GET, "/virtual-visits/tour-video/**").permitAll().antMatchers(HttpMethod.GET, "/virtual-visits/tour-image/**").permitAll();
        for(Rule rule: authorizationRules.getRules()){
            if(rule.getMethods().isEmpty()){
                interceptor=interceptor.antMatchers(rule.getPattern()).hasAnyAuthority(rule.getRoles().toArray(String[]::new));
            }else for(String method:rule.getMethods()){
                interceptor=interceptor.antMatchers(HttpMethod.resolve(method), rule.getPattern()).hasAnyAuthority(rule.getRoles().toArray(String[]::new));
            }
        }
        return interceptor.anyRequest().denyAll().and();
    }


    //for testing allowing access from all origins
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    //removing ROLE_ prefix
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }

}
