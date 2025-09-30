package com.wecp.financial_seminar_and_workshop_management.service;

import com.wecp.financial_seminar_and_workshop_management.entity.Enrollment;
import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.repository.EnrollmentRepository;
import com.wecp.financial_seminar_and_workshop_management.repository.EventRepository;
import com.wecp.financial_seminar_and_workshop_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
       @Autowired
          private EnrollmentRepository enrollmentRepository;
       
          @Autowired
          private EventRepository eventRepository;
       
          @Autowired
          private UserRepository userRepository;
       
          public Enrollment enrollInEvent(Long eventId, Long userId) {
              Event event = eventRepository.findById(eventId).orElseThrow();
              User user = userRepository.findById(userId).orElseThrow();
              Enrollment enrollment = new Enrollment();
              enrollment.setEvent(event);
              enrollment.setUser(user);
              return enrollmentRepository.save(enrollment);
          }

           public Enrollment enrollUser(Long eventId, Long userId) {
                  Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
                  User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
           
                  Enrollment enrollment = new Enrollment();
                  enrollment.setEvent(event);
                  enrollment.setUser(user);
           
                  return enrollmentRepository.save(enrollment);
              }

}
