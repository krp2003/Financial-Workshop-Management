package com.wecp.financial_seminar_and_workshop_management.controller;


import com.wecp.financial_seminar_and_workshop_management.dto.LoginRequest;
import com.wecp.financial_seminar_and_workshop_management.dto.LoginResponse;
import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.jwt.JwtUtil;
import com.wecp.financial_seminar_and_workshop_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


 
@RestController
public class RegisterAndLoginController {
 
     @Autowired
     private UserService userService;
  
     @Autowired
     private AuthenticationManager authenticationManager;
  
     @PostMapping("/register")
     public ResponseEntity<User> registerUser(@RequestBody User user) {
         User savedUser = userService.registerUser(user);
         return ResponseEntity.ok(savedUser);
     }
  
     @PostMapping("/login")
     public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
         try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             loginRequest.getUsername(), loginRequest.getPassword()
                     )
             );
             LoginResponse response = userService.login(loginRequest);
             return ResponseEntity.ok(response);
         } catch (AuthenticationException e) {
             return ResponseEntity.status(401).build();
         }
     }
}
 

