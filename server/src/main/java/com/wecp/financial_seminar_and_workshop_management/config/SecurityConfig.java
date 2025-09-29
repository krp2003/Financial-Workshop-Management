package com.wecp.financial_seminar_and_workshop_management.config;

import com.wecp.financial_seminar_and_workshop_management.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    // Implement security configuration here
    // /api/user/register and /api/user/login should be permitted to all
    // /api/institution/event should be permitted to INSTITUTION
    // /api/institution/event/{id} should be permitted to INSTITUTION
    // /api/institution/events should be permitted to INSTITUTION
    // /api/institution/event/{eventId}/resource should be permitted to INSTITUTION
    // /api/institution/event/professionals should be permitted to INSTITUTION
    // /api/institution/event/{eventId}/professional should be permitted to INSTITUTION
    // /api/professional/events should be permitted to PROFESSIONAL
    // /api/professional/event/{id}/status should be permitted to PROFESSIONAL
    // /api/professional/event/{eventId}/feedback should be permitted to PROFESSIONAL
    // /api/participant/events should be permitted to PARTICIPANT
    // /api/participant/event/{eventId}/enroll should be permitted to PARTICIPANT
    // /api/participant/event/{id}/status should be permitted to PARTICIPANT
    // /api/participant/event/{eventId}/feedback should be permitted to PARTICIPANT

    // Note: Use hasAuthority method to check the role of the user
    // for example, hasAuthority("INSTITUTION")

    @Autowired
        private UserDetailsService userDetailsService;
     
        @Autowired
        private PasswordEncoder passwordEncoder;
     
        @Autowired
        private JwtRequestFilter jwtRequestFilter;
     
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
     
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
     
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .authorizeRequests()
                    // Public endpoints
                    .antMatchers("/api/user/register", "/api/user/login").permitAll()
                    
                    // Institution endpoints
                    .antMatchers("/api/institution/event").hasAuthority("INSTITUTION")
                    .antMatchers("/api/institution/event/**").hasAuthority("INSTITUTION")
                    .antMatchers("/api/institution/events").hasAuthority("INSTITUTION")
                    .antMatchers("/api/institution/event/**/resource").hasAuthority("INSTITUTION")
                    .antMatchers("/api/institution/event/professionals").hasAuthority("INSTITUTION")
                    .antMatchers("/api/institution/event/**/professional").hasAuthority("INSTITUTION")
                    
                    // Professional endpoints
                    .antMatchers("/api/professional/events").hasAuthority("PROFESSIONAL")
                    .antMatchers("/api/professional/event/**/status").hasAuthority("PROFESSIONAL")
                    .antMatchers("/api/professional/event/**/feedback").hasAuthority("PROFESSIONAL")
                    
                    // Participant endpoints
                    .antMatchers("/api/participant/events").hasAuthority("PARTICIPANT")
                    .antMatchers("/api/participant/event/**/enroll").hasAuthority("PARTICIPANT")
                    .antMatchers("/api/participant/event/**/status").hasAuthority("PARTICIPANT")
                    .antMatchers("/api/participant/event/**/feedback").hasAuthority("PARTICIPANT")
                    
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     
            // Add JWT filter
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }
}