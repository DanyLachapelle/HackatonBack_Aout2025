package school.token.groupe7_hackatonback_aout2025.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.CalendarEventDto;
import school.token.groupe7_hackatonback_aout2025.application.dto.EventReminderDto;
import school.token.groupe7_hackatonback_aout2025.application.service.CalendarService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Contrôleur REST pour la gestion du calendrier - Couche Présentation (Layer 1/4)
 * Architecture 4 couches :
 * 1. Controller (Présentation) - Gère les requêtes HTTP et utilise des DTOs
 * 2. Service (Logique métier) - Contient la logique business
 * 3. Repository (Accès données) - Interface avec la base de données
 * 4. Model (Entités) - Représentation des données
 */
@RestController
@RequestMapping("/api/v2/calendar")
public class CalendarController {
    
    private final CalendarService calendarService;
    
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }
    
    // === ENDPOINTS POUR LES ÉVÉNEMENTS ===
    
    @GetMapping("/events")
    public ResponseEntity<List<CalendarEventDto>> getEvents(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<CalendarEventDto> events = calendarService.getEventsByUser(userId);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/events/range")
    public ResponseEntity<List<CalendarEventDto>> getEventsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            List<CalendarEventDto> events = calendarService.getEventsByDateRange(userId, start, end);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/events")
    public ResponseEntity<CalendarEventDto> createEvent(@RequestBody CalendarEventDto eventDto) {
        try {
            CalendarEventDto createdEvent = calendarService.createEvent(eventDto);
            return ResponseEntity.ok(createdEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/events/{eventId}")
    public ResponseEntity<CalendarEventDto> updateEvent(
            @PathVariable Long eventId,
            @RequestBody CalendarEventDto eventDto) {
        try {
            CalendarEventDto updatedEvent = calendarService.updateEvent(eventId, eventDto);
            return ResponseEntity.ok(updatedEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        try {
            calendarService.deleteEvent(eventId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/events/{eventId}")
    public ResponseEntity<CalendarEventDto> getEventById(@PathVariable Long eventId) {
        try {
            CalendarEventDto event = calendarService.getEventById(eventId);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // === ENDPOINTS POUR LES RAPPELS ===
    
    @PostMapping("/events/{eventId}/reminders")
    public ResponseEntity<EventReminderDto> createReminder(
            @PathVariable Long eventId,
            @RequestParam String reminderType) {
        try {
            EventReminderDto reminder = calendarService.createReminder(eventId, reminderType);
            return ResponseEntity.ok(reminder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/events/{eventId}/reminders")
    public ResponseEntity<List<EventReminderDto>> getEventReminders(@PathVariable Long eventId) {
        try {
            List<EventReminderDto> reminders = calendarService.getEventReminders(eventId);
            return ResponseEntity.ok(reminders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/reminders/pending")
    public ResponseEntity<List<EventReminderDto>> getPendingReminders(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<EventReminderDto> reminders = calendarService.getPendingReminders(userId);
            return ResponseEntity.ok(reminders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/reminders/to-send")
    public ResponseEntity<List<EventReminderDto>> getRemindersToSend() {
        try {
            List<EventReminderDto> reminders = calendarService.getRemindersToSend();
            return ResponseEntity.ok(reminders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/reminders/{reminderId}/mark-sent")
    public ResponseEntity<Void> markReminderAsSent(@PathVariable Long reminderId) {
        try {
            calendarService.markReminderAsSent(reminderId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/reminders/{reminderId}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long reminderId) {
        try {
            calendarService.deleteReminder(reminderId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 