package com.wecp.financial_seminar_and_workshop_management.service;


import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.User;
import com.wecp.financial_seminar_and_workshop_management.repository.EventRepository;
import com.wecp.financial_seminar_and_workshop_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
     @Autowired
        private EventRepository eventRepository;
     
        @Autowired
        private UserRepository userRepository;
     
        public Event createEvent(Event event) {
            return eventRepository.save(event);
        }
     
        public Event updateEvent(Long eventId, Event updatedEvent) {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Event not found"));
     
            event.setTitle(updatedEvent.getTitle());
            event.setDescription(updatedEvent.getDescription());
            event.setSchedule(updatedEvent.getSchedule());
            event.setLocation(updatedEvent.getLocation());
            event.setStatus(updatedEvent.getStatus());
     
            return eventRepository.save(event);
        }
     
        public List<Event> getEventsByInstitution(Long institutionId) {
            return eventRepository.findByInstitutionId(institutionId);
        }
     
        public List<Event> getAllEvents() {
            return eventRepository.findAll();
        }
     
        public Event assignProfessionalToEvent(Long eventId, Long userId) {
            Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
            User professional = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            event.getProfessionals().add(professional);
            return eventRepository.save(event);
        }
        public Event getEventById(Long id){
            return eventRepository.findById(id).orElseThrow();
        }
        public List<Event> getEventsForProfessional(Long userId){
            User prof=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
            return prof.getEvents();
        }
}
