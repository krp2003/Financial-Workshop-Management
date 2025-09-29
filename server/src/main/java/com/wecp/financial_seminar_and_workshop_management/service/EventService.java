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

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = eventRepository.findById(id).orElseThrow();
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

    public void assignProfessionalToEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        User professional = userRepository.findById(userId).orElseThrow();
        event.getProfessionals().add(professional);
        professional.getEvents().add(event);
        eventRepository.save(event);
        userRepository.save(professional);
    }

    public List<Event> getEventsByProfessional(Long userId) {
        return eventRepository.findByProfessionals_Id(userId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventStatus(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow();
    }
}