package com.wecp.financial_seminar_and_workshop_management.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;



@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String content;
 
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
 
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    // Constructors
    public Feedback() {}
    public Feedback(Event event, User user, String content, Date timestamp) {
        this.event = event;
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }
 
    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
 
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
 
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
 
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
 
