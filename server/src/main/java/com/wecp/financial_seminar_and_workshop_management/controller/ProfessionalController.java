package com.wecp.financial_seminar_and_workshop_management.controller;



import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Feedback;
import com.wecp.financial_seminar_and_workshop_management.service.EventService;
import com.wecp.financial_seminar_and_workshop_management.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 
@RestController
@RequestMapping("/api/professional")
@PreAuthorize("hasAuthority('PROFESSIONAL')")
public class ProfessionalController {
 
    @Autowired
    private EventService eventService;
 
    @Autowired
    private FeedbackService feedbackService;
 
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAssignedEvents(@RequestParam Long userId) {
        List<Event> events = eventService.getEventsForProfessional(userId);
        return ResponseEntity.ok(events);
    }
 
    @PostMapping("/event/{eventId}/feedback")
    public ResponseEntity<Void> provideFeedback(@PathVariable Long eventId,
                                                @RequestParam Long userId,
                                                @RequestBody Feedback feedback) {
        feedbackService.addFeedback(eventId, userId, feedback);
        return ResponseEntity.ok().build();
    }
}
