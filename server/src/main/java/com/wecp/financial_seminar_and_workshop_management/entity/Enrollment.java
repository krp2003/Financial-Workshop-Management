package com.wecp.financial_seminar_and_workshop_management.entity;

import javax.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String status; // PENDING, APPROVED, REJECTED
 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
 
    public Enrollment(String status, User user, Event event) {
        this.status = status;
        this.user = user;
        this.event = event;
    }

    public Enrollment() {}
    
    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
 
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
 
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}
 



