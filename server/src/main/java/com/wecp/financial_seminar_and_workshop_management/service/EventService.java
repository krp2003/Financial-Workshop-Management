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
     
        public Event updateEvent(Long id, Event eventDetails) {
            Event event = eventRepository.findById(id).orElseThrow();
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setSchedule(eventDetails.getSchedule());
            event.setLocation(eventDetails.getLocation());
            event.setStatus(eventDetails.getStatus());
            return eventRepository.save(event);
        }
     
        public Optional<Event> getEvent(Long id) {
            return eventRepository.findById(id);
        }
     
        public List<Event> getEventsByInstitution(Long institutionId) {
            return eventRepository.findByInstitutionId(institutionId);
        }
     
        public List<Event> getEventsByProfessional(Long userId) {
            return eventRepository.findByProfessionals_Id(userId);
        }
     
        public Event assignProfessional(Long eventId, Long userId) {
            Event event = eventRepository.findById(eventId).orElseThrow();
            User professional = userRepository.findById(userId).orElseThrow();
            event.getProfessionals().add(professional);
            return eventRepository.save(event);
        }
     
        public Event updateEventStatus(Long id, String status) {
            Event event = eventRepository.findById(id).orElseThrow();
            event.setStatus(status);
            return eventRepository.save(event);
        }

        public List<Event> getAllEvents(){
            return eventRepository.findAll();
        }
}
