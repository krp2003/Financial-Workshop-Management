package com.wecp.financial_seminar_and_workshop_management.repository;


import com.wecp.financial_seminar_and_workshop_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findByRole(String role); 
}
