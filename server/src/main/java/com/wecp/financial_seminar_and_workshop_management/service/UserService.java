package com.wecp.financial_seminar_and_workshop_management.service;

import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService{

      @Autowired
        private UserRepository userRepository;
     
        public List<User> getAllProfessionals() {
            return userRepository.findByRole("PROFESSIONAL");
        }
     
        public User registerUser(User user) {
            return userRepository.save(user);
        }
     
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            User user= userRepository.findByUsername(username);
            if(user== null){
                throw new UsernameNotFoundException("User not found with username "+username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
        }

        public User getUserByUsername(String username){
            return userRepository.findByUsername(username);
        }
}
