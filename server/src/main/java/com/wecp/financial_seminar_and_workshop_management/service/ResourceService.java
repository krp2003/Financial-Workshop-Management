package com.wecp.financial_seminar_and_workshop_management.service;


import com.wecp.financial_seminar_and_workshop_management.entity.Event;
import com.wecp.financial_seminar_and_workshop_management.entity.Resource;
import com.wecp.financial_seminar_and_workshop_management.repository.EventRepository;
import com.wecp.financial_seminar_and_workshop_management.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
 
    @Autowired
    private EventRepository eventRepository;
 
    public Resource addResource(Long eventId, Resource resource) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        resource.setEvent(event);
        return resourceRepository.save(resource);
    }
}
