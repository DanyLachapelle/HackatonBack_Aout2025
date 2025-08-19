package school.token.groupe7_hackatonback_aout2025.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.token.groupe7_hackatonback_aout2025.domain.File;
import school.token.groupe7_hackatonback_aout2025.domain.Folder;
import school.token.groupe7_hackatonback_aout2025.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    
    List<File> findByUserOrderByName(User user);
    
    List<File> findByUserAndFolderOrderByName(User user, Folder folder);
    
    List<File> findByUserAndFolderIsNullOrderByName(User user);
    
    Optional<File> findByPath(String path);
    
    Optional<File> findByPathAndUser(String path, User user);
    
    boolean existsByPath(String path);
    
    boolean existsByPathAndUser(String path, User user);
    
    List<File> findByUserAndIsFavoriteTrueOrderByName(User user);
    
    // Recherche par type de contenu (pour Galerie, Music Player, etc.)
    List<File> findByUserAndContentTypeLikeOrderByUpdatedAtDesc(User user, String contentTypePattern);
    
    List<File> findByUserAndContentTypeLikeOrderByName(User user, String contentTypePattern);
    
    // Recherche par nom ou chemin
    @Query("SELECT f FROM File f WHERE f.user = :user AND (f.name LIKE %:searchTerm% OR f.path LIKE %:searchTerm%)")
    List<File> searchByUserAndNameOrPath(@Param("user") User user, @Param("searchTerm") String searchTerm);
    
    // Recherche par nom (pour la fonctionnalité de recherche)
    List<File> findByUserAndNameContainingIgnoreCaseOrderByName(User user, String searchTerm);
    
    // Recherche par type de contenu et terme de recherche
    @Query("SELECT f FROM File f WHERE f.user = :user AND f.contentType LIKE :contentTypePattern AND (f.name LIKE %:searchTerm% OR f.path LIKE %:searchTerm%)")
    List<File> searchByUserAndContentTypeAndNameOrPath(@Param("user") User user, @Param("contentTypePattern") String contentTypePattern, @Param("searchTerm") String searchTerm);
    
    long countByUserAndFolderIsNull(User user);
    
    long countByUserAndFolder(User user, Folder folder);

    List<File> findByFolderId(Long folderId);

    List<File> findByUserOrderByUpdatedAtDesc(User user);
}