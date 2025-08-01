package school.token.groupe7_hackatonback_aout2025.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_reminder")
public class EventReminder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private CalendarEvent event;
    
    @Column(name = "reminder_time", nullable = false)
    private LocalDateTime reminderTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "reminder_type", nullable = false)
    private ReminderType reminderType = ReminderType.FIFTEEN_MIN;
    
    @Column(name = "is_sent", nullable = false)
    private Boolean isSent = false;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public enum ReminderType {
        FIVE_MIN("5min"),
        FIFTEEN_MIN("15min"),
        THIRTY_MIN("30min"),
        ONE_HOUR("1hour"),
        ONE_DAY("1day");
        
        private final String value;
        
        ReminderType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static ReminderType fromValue(String value) {
            for (ReminderType type : values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
            return FIFTEEN_MIN; // Default
        }
    }
    
    // Constructeurs
    public EventReminder() {
        this.createdAt = LocalDateTime.now();
    }
    
    public EventReminder(CalendarEvent event, LocalDateTime reminderTime, ReminderType reminderType) {
        this();
        this.event = event;
        this.reminderTime = reminderTime;
        this.reminderType = reminderType;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public CalendarEvent getEvent() {
        return event;
    }
    
    public void setEvent(CalendarEvent event) {
        this.event = event;
    }
    
    public LocalDateTime getReminderTime() {
        return reminderTime;
    }
    
    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
    
    public ReminderType getReminderType() {
        return reminderType;
    }
    
    public void setReminderType(ReminderType reminderType) {
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
} 