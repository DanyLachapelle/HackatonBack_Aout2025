package school.token.groupe7_hackatonback_aout2025.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.token.groupe7_hackatonback_aout2025.model.EventReminder;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventReminderRepository extends JpaRepository<EventReminder, Long> {
    
    // Trouver tous les rappels d'un événement
    List<EventReminder> findByEventIdOrderByReminderTimeAsc(Long eventId);
    
    // Trouver les rappels non envoyés pour un utilisateur
    @Query("SELECT er FROM EventReminder er WHERE er.event.user.id = :userId AND er.isSent = false ORDER BY er.reminderTime ASC")
    List<EventReminder> findPendingRemindersByUserId(@Param("userId") Long userId);
    
    // Trouver les rappels à envoyer (reminderTime <= now et non envoyés)
    @Query("SELECT er FROM EventReminder er WHERE er.reminderTime <= :now AND er.isSent = false ORDER BY er.reminderTime ASC")
    List<EventReminder> findRemindersToSend(@Param("now") LocalDateTime now);
    
    // Supprimer tous les rappels d'un événement
    void deleteByEventId(Long eventId);
    
    // Compter les rappels non envoyés pour un événement
    long countByEventIdAndIsSentFalse(Long eventId);
} 