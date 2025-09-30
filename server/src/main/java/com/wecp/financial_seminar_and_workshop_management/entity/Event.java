package com.wecp.financial_seminar_and_workshop_management.entity;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // @Column(name="institution_id",insertable=false,updatable = false)
    private Long institutionId;
    private String title;
    private String description;
    private String schedule;
    private String location;
    private String status; // PENDING, IN_PROGRESS, COMPLETED
 
 
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> resources = new ArrayList<>();
 
    @ManyToMany
    @JoinTable(
        name = "event_professionals",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> professionals = new ArrayList<>();
 
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();
 
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name= "institutionId")
    private User institution;
 
    public Event(Long institutionId, String title, String description, String schedule, String location, String status,
            List<Resource> resources, List<User> professionals, List<Enrollment> enrollments,
            List<Feedback> feedbacks) {
        this.institutionId = institutionId;
        this.title = title;
        this.description = description;
        this.schedule = schedule;
        this.location = location;
        this.status = status;
        this.resources = resources;
        this.professionals = professionals;
        this.enrollments = enrollments;
        this.feedbacks = feedbacks;
    }

    // Constructors
    public Event() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<User> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(List<User> professionals) {
        this.professionals = professionals;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
   
    
 
   
}
 

