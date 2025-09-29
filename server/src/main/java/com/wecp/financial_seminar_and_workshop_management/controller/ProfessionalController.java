package com.wecp.financial_seminar_and_workshop_management.controller;



import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Feedback;
import com.wecp.financial_seminar_and_workshop_management.service.EventService;
import com.wecp.financial_seminar_and_workshop_management.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/professional")
public class ProfessionalController {
 
    @Autowired
    EventService eventService;
 
    @Autowired
    FeedbackService feedbackService;
 
    // View assigned events
    @GetMapping("/events")
    public ResponseEntity<List<Event>> viewAssignedEvents(@RequestParam Long userId) {
        return ResponseEntity.ok(eventService.getEventsByProfessional(userId));
    }
 
    // Update event status
    @PutMapping("/event/{id}/status")
    public ResponseEntity<Event> updateEventStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(eventService.updateEventStatus(id, status));
    }
 
    // Provide feedback
    @PostMapping("/event/{eventId}/feedback")
    public ResponseEntity<Feedback> provideFeedback(@PathVariable Long eventId,
                                                    @RequestParam Long userId,
                                                    @RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.addFeedback(userId, eventId, feedback));
    }
}
 
