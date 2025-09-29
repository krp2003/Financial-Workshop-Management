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
       private JwtUtil jwtUtil;
    
       @Autowired
       private AuthenticationManager authenticationManager;
    
       // Register User
       @PostMapping("/api/user/register")
       public ResponseEntity<User> registerUser(@RequestBody User user) {
           return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
       }
    
       // Login User
       @PostMapping("/api/user/login")
       public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
           try {
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
               );
           } catch (AuthenticationException e) {
               throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
           }
    
           UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
           String token = jwtUtil.generateToken(userDetails.getUsername());
           User user = userService.getUserByUsername(loginRequest.getUsername());
           LoginResponse loginResponse = new LoginResponse(user.getId(),token,user.getUsername(), user.getEmail(), user.getRole());
           return new ResponseEntity<>(loginResponse, HttpStatus.OK);
       }
}
 

