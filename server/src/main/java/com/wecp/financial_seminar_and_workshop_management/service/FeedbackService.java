package com.wecp.financial_seminar_and_workshop_management.service;



import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Feedback;
import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.repository.EventRepository;
import com.wecp.financial_seminar_and_workshop_management.repository.FeedbackRepository;
import com.wecp.financial_seminar_and_workshop_management.repository.UserRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

     @Autowired
        private FeedbackRepository feedbackRepository;
     
        @Autowired
        private UserRepository userRepository;
     
        @Autowired
        private EventRepository eventRepository;

        public Feedback addFeedback(Long userId, Long eventId, Feedback feedback) {
                Event event = eventRepository.findById(eventId).orElseThrow();
                User user = userRepository.findById(userId).orElseThrow();
                feedback.setEvent(event);
                feedback.setUser(user);
                feedback.setTimestamp(new Date());
                return feedbackRepository.save(feedback);
            }

}
