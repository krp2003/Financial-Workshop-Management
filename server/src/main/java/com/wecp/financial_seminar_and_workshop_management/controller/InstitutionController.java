package com.wecp.financial_seminar_and_workshop_management.controller;



import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Resource;
import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.service.EventService;
import com.wecp.financial_seminar_and_workshop_management.service.ResourceService;
import com.wecp.financial_seminar_and_workshop_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 
@RestController
@RequestMapping("/api/institution")
@PreAuthorize("hasAuthority('INSTITUTION')")
public class InstitutionController {
 
    @Autowired
    EventService eventService;
 
    @Autowired
    ResourceService resourceService;
 
    @Autowired
    UserService userService;
  @PostMapping("/event")
     public ResponseEntity<Event> createEvent(@RequestBody Event event) {
         Event savedEvent = eventService.createEvent(event);
         return ResponseEntity.ok(savedEvent);
     }
  
     @PutMapping("/event/{eventId}")
     public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
         Event updated = eventService.updateEvent(eventId, event);
         return ResponseEntity.ok(updated);
     }
  
     @GetMapping("/events")
     public ResponseEntity<List<Event>> getEvents(@RequestParam Long institutionId) {
         List<Event> events = eventService.getEventsByInstitution(institutionId);
         return ResponseEntity.ok(events);
     }
  
     @PostMapping("/event/{eventId}/resource")
     public ResponseEntity<Resource> addResource(@PathVariable Long eventId, @RequestBody Resource resource) {
         Resource saved = resourceService.addResource(eventId, resource);
         return ResponseEntity.ok(saved);
     }
  
     @GetMapping("/event/professionals")
     public ResponseEntity<List<User>> getProfessionals() {
         List<User> professionals = userService.getUsersByRole("PROFESSIONAL");
         return ResponseEntity.ok(professionals);
     }
  
     @PostMapping("/event/{eventId}/professional")
     public ResponseEntity<Void> assignProfessional(@PathVariable Long eventId, @RequestParam Long userId) {
         eventService.assignProfessionalToEvent(eventId, userId);
         return ResponseEntity.ok().build();
     }
}
 

