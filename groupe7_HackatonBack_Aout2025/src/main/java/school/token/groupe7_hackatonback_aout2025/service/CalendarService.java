package school.token.groupe7_hackatonback_aout2025.service;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.dto.CalendarEventDto;
import school.token.groupe7_hackatonback_aout2025.dto.EventReminderDto;
import school.token.groupe7_hackatonback_aout2025.mapper.EntityMapper;
import school.token.groupe7_hackatonback_aout2025.model.CalendarEvent;
import school.token.groupe7_hackatonback_aout2025.model.EventReminder;
import school.token.groupe7_hackatonback_aout2025.model.User;
import school.token.groupe7_hackatonback_aout2025.repository.CalendarEventRepository;
import school.token.groupe7_hackatonback_aout2025.repository.EventReminderRepository;
import school.token.groupe7_hackatonback_aout2025.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service de gestion du calendrier - Couche Business Logic
 * Respecte l'architecture 4 couches
 */
@Service
public class CalendarService {
    
    private final CalendarEventRepository calendarEventRepository;
    private final EventReminderRepository eventReminderRepository;
    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    
    public CalendarService(CalendarEventRepository calendarEventRepository,
                         EventReminderRepository eventReminderRepository,
                         UserRepository userRepository, 
                         EntityMapper entityMapper) {
        this.calendarEventRepository = calendarEventRepository;
        this.eventReminderRepository = eventReminderRepository;
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }
    
    // === GESTION DES ÉVÉNEMENTS ===
    
    public List<CalendarEventDto> getEventsByUser(Long userId) {
        User user = getUserById(userId);
        List<CalendarEvent> events = calendarEventRepository.findByUserOrderByStartDate(user);
        return events.stream()
                .map(entityMapper::toDto)
                .toList();
    }
    
    public List<CalendarEventDto> getEventsByDateRange(Long userId, LocalDateTime start, LocalDateTime end) {
        User user = getUserById(userId);
        List<CalendarEvent> events = calendarEventRepository.findEventsInDateRange(user, start, end);
        return events.stream()
                .map(entityMapper::toDto)
                .toList();
    }
    
    public CalendarEventDto createEvent(CalendarEventDto eventDto) {
        User user = getUserById(eventDto.getUserId());
        
        CalendarEvent event = new CalendarEvent();
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setColor(eventDto.getColor());
        event.setIsAllDay(eventDto.getIsAllDay());
        event.setLocation(eventDto.getLocation());
        event.setUser(user);
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        
        CalendarEvent savedEvent = calendarEventRepository.save(event);
        return entityMapper.toDto(savedEvent);
    }
    
    public CalendarEventDto updateEvent(Long eventId, CalendarEventDto eventDto) {
        CalendarEvent event = calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
        
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setColor(eventDto.getColor());
        event.setIsAllDay(eventDto.getIsAllDay());
        event.setLocation(eventDto.getLocation());
        event.setUpdatedAt(LocalDateTime.now());
        
        CalendarEvent savedEvent = calendarEventRepository.save(event);
        return entityMapper.toDto(savedEvent);
    }
    
    public void deleteEvent(Long eventId) {
        CalendarEvent event = calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
        
        // Supprimer tous les rappels associés
        eventReminderRepository.deleteByEventId(eventId);
        
        // Supprimer l'événement
        calendarEventRepository.delete(event);
    }
    
    public CalendarEventDto getEventById(Long eventId) {
        CalendarEvent event = calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
        return entityMapper.toDto(event);
    }
    
    // === GESTION DES RAPPELS ===
    
    public EventReminderDto createReminder(Long eventId, String reminderType) {
        CalendarEvent event = calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
        
        EventReminder.ReminderType type = EventReminder.ReminderType.fromValue(reminderType);
        LocalDateTime reminderTime = calculateReminderTime(event.getStartDate(), type);
        
        EventReminder reminder = new EventReminder(event, reminderTime, type);
        EventReminder savedReminder = eventReminderRepository.save(reminder);
        
        return toReminderDto(savedReminder);
    }
    
    public List<EventReminderDto> getEventReminders(Long eventId) {
        List<EventReminder> reminders = eventReminderRepository.findByEventIdOrderByReminderTimeAsc(eventId);
        return reminders.stream()
                .map(this::toReminderDto)
                .collect(Collectors.toList());
    }
    
    public List<EventReminderDto> getPendingReminders(Long userId) {
        List<EventReminder> reminders = eventReminderRepository.findPendingRemindersByUserId(userId);
        return reminders.stream()
                .map(this::toReminderDto)
                .collect(Collectors.toList());
    }
    
    public List<EventReminderDto> getRemindersToSend() {
        List<EventReminder> reminders = eventReminderRepository.findRemindersToSend(LocalDateTime.now());
        return reminders.stream()
                .map(this::toReminderDto)
                .collect(Collectors.toList());
    }
    
    public void markReminderAsSent(Long reminderId) {
        EventReminder reminder = eventReminderRepository.findById(reminderId)
                .orElseThrow(() -> new RuntimeException("Rappel non trouvé"));
        reminder.setIsSent(true);
        eventReminderRepository.save(reminder);
    }
    
    public void deleteReminder(Long reminderId) {
        EventReminder reminder = eventReminderRepository.findById(reminderId)
                .orElseThrow(() -> new RuntimeException("Rappel non trouvé"));
        eventReminderRepository.delete(reminder);
    }
    
    public void deleteEventReminders(Long eventId) {
        eventReminderRepository.deleteByEventId(eventId);
    }
    
    // === MÉTHODES UTILITAIRES ===
    
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
    
    private LocalDateTime calculateReminderTime(LocalDateTime eventTime, EventReminder.ReminderType type) {
        return switch (type) {
            case FIVE_MIN -> eventTime.minusMinutes(5);
            case FIFTEEN_MIN -> eventTime.minusMinutes(15);
            case THIRTY_MIN -> eventTime.minusMinutes(30);
            case ONE_HOUR -> eventTime.minusHours(1);
            case ONE_DAY -> eventTime.minusDays(1);
        };
    }
    
    private EventReminderDto toReminderDto(EventReminder reminder) {
        EventReminderDto dto = new EventReminderDto();
        dto.setId(reminder.getId());
        dto.setEventId(reminder.getEvent().getId());
        dto.setReminderTime(reminder.getReminderTime());
        dto.setReminderType(reminder.getReminderType().getValue());
        dto.setIsSent(reminder.getIsSent());
        dto.setCreatedAt(reminder.getCreatedAt());
        dto.setEventTitle(reminder.getEvent().getTitle());
        dto.setEventStartDate(reminder.getEvent().getStartDate());
        return dto;
    }
} 