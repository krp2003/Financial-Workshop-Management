package com.wecp.financial_seminar_and_workshop_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 
    // Constructors
    public Enrollment() {}
    public Enrollment(User user, Event event, String status) {
        this.user = user;
        this.event = event;
        this.status = status;
    }
 
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
 



