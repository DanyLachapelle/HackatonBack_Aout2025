package school.token.groupe7_hackatonback_aout2025.application.dto;

import java.time.LocalDateTime;

public class EventReminderDto {
    private Long id;
    private Long eventId;
    private LocalDateTime reminderTime;
    private String reminderType;
    private Boolean isSent;
    private LocalDateTime createdAt;
    
    // Informations de l'événement associé
    private String eventTitle;
    private LocalDateTime eventStartDate;
    
    // Constructeurs
    public EventReminderDto() {}
    
    public EventReminderDto(Long id, Long eventId, LocalDateTime reminderTime, String reminderType, Boolean isSent) {
        this.id = id;
        this.eventId = eventId;
        this.reminderTime = reminderTime;
        this.reminderType = reminderType;
        this.isSent = isSent;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEventId() {
        return eventId;
    }
    
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    
    public LocalDateTime getReminderTime() {
        return reminderTime;
    }
    
    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
    
    public String getReminderType() {
        return reminderType;
    }
    
    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }
    
    public Boolean getIsSent() {
        return isSent;
    }
    
    public void setIsSent(Boolean sent) {
        isSent = sent;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getEventTitle() {
        return eventTitle;
    }
    
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    
    public LocalDateTime getEventStartDate() {
        return eventStartDate;
    }
    
    public void setEventStartDate(LocalDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }
} 