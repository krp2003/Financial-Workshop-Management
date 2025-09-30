package com.wecp.financial_seminar_and_workshop_management.service;

import com.wecp.financial_seminar_and_workshop_management.dto.LoginRequest;
import com.wecp.financial_seminar_and_workshop_management.dto.LoginResponse;
import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.jwt.JwtUtil;
import com.wecp.financial_seminar_and_workshop_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

       @Autowired
         private UserRepository userRepository;
      
         @Autowired
         private PasswordEncoder passwordEncoder;

         @Autowired
         @Lazy
         private AuthenticationManager authenticationManager;

         @Autowired
         private JwtUtil jwtUtil;
      
         // For UserDetailsService
         @Override
         public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             User user = userRepository.findByUsername(username)
                             .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
      
             return new org.springframework.security.core.userdetails.User(
                     user.getUsername(),
                     user.getPassword(),
                     Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
             );
         }
      
         // Register user
         public User registerUser(User user) {
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             return userRepository.save(user);
         }
      
         // Find all users
         public List<User> getAllUsers() {
             return userRepository.findAll();
         }
      
         // Find by role
         public List<User> getUsersByRole(String role) {
             return userRepository.findByRole(role);
         }
      
         // Find by ID
         public User getUserById(Long userId) {
             return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
         }

         public LoginResponse login(LoginRequest loginRequest) {
                 try {
                     authenticationManager.authenticate(
                             new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                     );
                 } catch (BadCredentialsException e) {
                     throw new RuntimeException("Incorrect username or password");
                 }
          
                 User user = userRepository.findByUsername(loginRequest.getUsername())
                         .orElseThrow(() -> new RuntimeException("User not found"));
          
                 String token = jwtUtil.generateToken(user.getUsername());
          
                 return new LoginResponse(user.getId(),token,user.getUsername(),user.getEmail(),user.getRole());
             }
}
