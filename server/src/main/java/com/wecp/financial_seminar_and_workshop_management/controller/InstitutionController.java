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
import org.springframework.web.bind.annotation.*;

import java.util.List;

 
@RestController
@RequestMapping("/api/institution")
public class InstitutionController {
 
    @Autowired
    EventService eventService;
 
    @Autowired
    ResourceService resourceService;
 
    @Autowired
    UserService userService;
 
    // Create Event
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.OK);
    }
 
    // Update Event
    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        return new ResponseEntity<>(eventService.updateEvent(id, eventDetails), HttpStatus.OK);
    }
 
    // Get Events for Institution
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getInstitutionsEvents(@RequestParam Long institutionId) {
        return new ResponseEntity<>(eventService.getEventsByInstitution(institutionId), HttpStatus.OK);
    }
 
    // Add Resource to Event
    @PostMapping("/event/{eventId}/resource")
    public ResponseEntity<Resource> addResourceToEvent(@PathVariable Long eventId, @RequestBody Resource resource) {
        Event event = eventService.getEvent(eventId).orElseThrow();
        resource.setEvent(event);
        return new ResponseEntity<>(resourceService.addResource(resource), HttpStatus.OK);
    }
 
    // Get Professionals List
    @GetMapping("/event/professionals")
    public ResponseEntity<List<User>> getProfessionalsList() {
        return new ResponseEntity<>(userService.getAllProfessionals(), HttpStatus.OK);
    }
 
    // Assign Professional to Event
    @PostMapping("/event/{eventId}/professional")
    public ResponseEntity<Event> assignProfessionalToEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        return new ResponseEntity<>(eventService.assignProfessional(eventId, userId), HttpStatus.OK);
    }
}
 

