package school.token.groupe7_hackatonback_aout2025.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.token.groupe7_hackatonback_aout2025.domain.Folder;
import school.token.groupe7_hackatonback_aout2025.domain.SystemFolderType;
import school.token.groupe7_hackatonback_aout2025.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    
    List<Folder> findByUserOrderByName(User user);
    
    List<Folder> findByUserAndParentFolderIsNullOrderByName(User user);
    
    List<Folder> findByUserAndParentFolderOrderByName(User user, Folder parentFolder);
    
    Optional<Folder> findByPath(String path);
    
    Optional<Folder> findByPathAndUser(String path, User user);
    
    boolean existsByPath(String path);
    
    boolean existsByPathAndUser(String path, User user);
    
    List<Folder> findByUserAndIsFavoriteTrueOrderByName(User user);
    
    @Query("SELECT f FROM Folder f WHERE f.user = :user AND (f.name LIKE %:searchTerm% OR f.path LIKE %:searchTerm%)")
    List<Folder> searchByUserAndNameOrPath(@Param("user") User user, @Param("searchTerm") String searchTerm);
    
    List<Folder> findByUserAndNameContainingIgnoreCaseOrderByName(User user, String searchTerm);
    
    // Méthodes pour les dossiers système
    boolean existsByUserAndIsSystemFolderTrue(User user);
    
    boolean existsByUserAndSystemFolderType(User user, SystemFolderType systemFolderType);
    
    List<Folder> findByUserAndIsSystemFolderTrueOrderByName(User user);
    
    List<Folder> findByUserAndIsSystemFolderFalseAndParentFolderIsNullOrderByName(User user);
    
    Optional<Folder> findByUserAndSystemFolderType(User user, SystemFolderType systemFolderType);
    
    long countByUserAndParentFolderIsNull(User user);
    
    long countByUserAndParentFolder(User user, Folder parentFolder);
    
    long countByUserAndIsSystemFolderTrue(User user);
} 