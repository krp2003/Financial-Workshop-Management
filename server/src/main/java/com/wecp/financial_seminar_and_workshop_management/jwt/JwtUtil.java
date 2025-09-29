package com.wecp.financial_seminar_and_workshop_management.jwt;

import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

@Component
public class JwtUtil {
 
    private final String SECRET_KEY = "mySecretKey12345"; // 🔐 Keep safe in config/env
     
        // Token validity (e.g., 10 hours)
        private final long JWT_EXPIRATION = 1000 * 60 * 60 * 10;
     
        // Generate token
        public String generateToken(String username) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, username);
        }
     
        private String createToken(Map<String, Object> claims, String subject) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)  // username
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }
     
        // Extract username from token
        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }
     
        // Extract expiration date
        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }
     
        // Extract a claim with a function
        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }
     
        private Claims extractAllClaims(String token) {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        }
     
        // Check if token expired
        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }
     
        // Validate token
        public Boolean validateToken(String token, String username) {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        }
}
 