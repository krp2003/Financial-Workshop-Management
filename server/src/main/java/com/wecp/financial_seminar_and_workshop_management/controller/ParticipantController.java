package com.wecp.financial_seminar_and_workshop_management.controller;


import com.wecp.financial_seminar_and_workshop_management.entity.Enrollment;
import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Feedback;
import com.wecp.financial_seminar_and_workshop_management.service.EnrollmentService;
import com.wecp.financial_seminar_and_workshop_management.service.EventService;
import com.wecp.financial_seminar_and_workshop_management.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/participant")
public class ParticipantController {
 
    @Autowired
    EventService eventService;
 
    @Autowired
    EnrollmentService enrollmentService;
 
    @Autowired
    FeedbackService feedbackService;
 
    // Get all events
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
 
    // Enroll in event
    @PostMapping("/event/{eventId}/enroll")
    public ResponseEntity<Enrollment> enrollInEvent(@RequestParam Long userId, @PathVariable Long eventId) {
        return ResponseEntity.ok(enrollmentService.enroll(userId, eventId));
    }
 
    // View event status
    @GetMapping("/event/{id}/status")
    public ResponseEntity<Event> viewEventStatus(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEvent(id).orElseThrow());
    }
 
    // Provide feedback
    @PostMapping("/event/{eventId}/feedback")
    public ResponseEntity<Feedback> provideFeedback(@RequestParam Long userId,
                                                    @PathVariable Long eventId,
                                                    @RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.addFeedback(userId, eventId, feedback));
    }
}
 