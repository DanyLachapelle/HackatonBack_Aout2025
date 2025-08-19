package school.token.groupe7_hackatonback_aout2025.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.token.groupe7_hackatonback_aout2025.domain.File;
import school.token.groupe7_hackatonback_aout2025.domain.FileSelection;
import school.token.groupe7_hackatonback_aout2025.domain.User;

import java.util.List;

@Repository
public interface FileSelectionRepository extends JpaRepository<FileSelection, Long> {
    
    List<FileSelection> findByUserOrderBySelectedAtDesc(User user);
    
    List<FileSelection> findByUserAndFile(User user, File file);
    
    void deleteByUserAndFile(User user, File file);
    
    void deleteByUser(User user);
} 