package com.wecp.financial_seminar_and_workshop_management.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wecp.financial_seminar_and_workshop_management.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .antMatchers("/api/user/register", "/api/user/login").permitAll()
                .antMatchers("/api/institution/**").hasAuthority("INSTITUTION")
                .antMatchers("/api/professional/**").hasAuthority("PROFESSIONAL")
                .antMatchers("/api/participant/**").hasAuthority("PARTICIPANT")
                .anyRequest().authenticated()
            .and()
            .addFilterBefore((Filter) jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}