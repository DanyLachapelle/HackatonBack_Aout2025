package school.token.groupe7_hackatonback_aout2025.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.token.groupe7_hackatonback_aout2025.domain.CalendarEvent;
import school.token.groupe7_hackatonback_aout2025.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {
    
    List<CalendarEvent> findByUserOrderByStartDate(User user);
    
    List<CalendarEvent> findByUserAndStartDateBetweenOrderByStartDate(User user, LocalDateTime start, LocalDateTime end);
    
    List<CalendarEvent> findByUserAndStartDateGreaterThanEqualOrderByStartDate(User user, LocalDateTime start);
    
    @Query("SELECT e FROM CalendarEvent e WHERE e.user = :user AND " +
           "((e.startDate BETWEEN :startDate AND :endDate) OR " +
           "(e.endDate BETWEEN :startDate AND :endDate) OR " +
           "(e.startDate <= :startDate AND e.endDate >= :endDate)) " +
           "ORDER BY e.startDate")
    List<CalendarEvent> findEventsInDateRange(@Param("user") User user, 
                                             @Param("startDate") LocalDateTime startDate, 
                                             @Param("endDate") LocalDateTime endDate);
} 