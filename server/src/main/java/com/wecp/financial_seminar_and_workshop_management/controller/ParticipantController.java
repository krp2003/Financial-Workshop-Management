package com.wecp.financial_seminar_and_workshop_management.controller;


import com.wecp.financial_seminar_and_workshop_management.entity.Enrollment;
import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Feedback;
import com.wecp.financial_seminar_and_workshop_management.service.EnrollmentService;
import com.wecp.financial_seminar_and_workshop_management.service.EventService;
import com.wecp.financial_seminar_and_workshop_management.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 
@RestController
@RequestMapping("/api/participant")
@PreAuthorize("hasAuthority('PARTICIPANT')")
public class ParticipantController {
 
    @Autowired
    private EventService eventService;
 
    @Autowired
    private EnrollmentService enrollmentService;
 
    @Autowired
    private FeedbackService feedbackService;
 
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
 
    @PostMapping("/event/{eventId}/enroll")
    public ResponseEntity<Void> enrollInEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        enrollmentService.enrollInEvent(userId, eventId);
        return ResponseEntity.ok().build();
    }
 
    @GetMapping("/event/{eventId}/status")
    public ResponseEntity<Event> viewEventStatus(@PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }
 
    @PostMapping("/event/{eventId}/feedback")
    public ResponseEntity<Void> provideFeedback(@PathVariable Long eventId,
                                                @RequestParam Long userId,
                                                @RequestBody Feedback feedback) {
        feedbackService.addFeedback(eventId, userId, feedback);
        return ResponseEntity.ok().build();
    }
}
