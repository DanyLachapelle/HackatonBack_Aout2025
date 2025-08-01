package school.token.groupe7_hackatonback_aout2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.token.groupe7_hackatonback_aout2025.model.File;
import school.token.groupe7_hackatonback_aout2025.model.FileSelection;
import school.token.groupe7_hackatonback_aout2025.model.User;

import java.util.List;

@Repository
public interface FileSelectionRepository extends JpaRepository<FileSelection, Long> {
    
    List<FileSelection> findByUserOrderBySelectedAtDesc(User user);
    
    List<FileSelection> findByUserAndFile(User user, File file);
    
    void deleteByUserAndFile(User user, File file);
    
    void deleteByUser(User user);
} 