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

public class UserService {

    @Autowired

    private UserRepository userRepository;

    public User registerUser(User user) {

        return userRepository.save(user);

    }

    public User loginUser(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password).orElse(null);

    }

    public List<User> getProfessionals() {

        return userRepository.findByRole("PROFESSIONAL");

    }

}