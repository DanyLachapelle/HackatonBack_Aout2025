package school.token.groupe7_hackatonback_aout2025.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.token.groupe7_hackatonback_aout2025.domain.Folder;
import school.token.groupe7_hackatonback_aout2025.domain.SystemFolderType;
import school.token.groupe7_hackatonback_aout2025.domain.User;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FolderRepository;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class SystemFolderService {

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final String uploadDir = "uploads"; // TODO: Configurer via application.properties

    public SystemFolderService(FolderRepository folderRepository, UserRepository userRepository) {
        this.folderRepository = folderRepository;
        this.userRepository = userRepository;
    }

    /**
     * Initialise les dossiers système pour un utilisateur
     */
    @Transactional
    public void initializeSystemFolders(Long userId) {
        System.out.println("🔧 initializeSystemFolders appelé pour userId: " + userId);
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        System.out.println("👤 Utilisateur trouvé: " + user.getUsername());

        // Vérifier si les dossiers système existent déjà en comptant
        long systemFoldersCount = folderRepository.countByUserAndIsSystemFolderTrue(user);
        System.out.println("🗂️ Nombre de dossiers système existants: " + systemFoldersCount);
        
        if (systemFoldersCount >= SystemFolderType.values().length) {
            System.out.println("✅ Dossiers système déjà initialisés, sortie");
            return; // Dossiers déjà initialisés
        }

        System.out.println("🏗️ Création des dossiers système...");
        // Créer les dossiers système
        for (SystemFolderType folderType : SystemFolderType.values()) {
            System.out.println("📁 Création du dossier: " + folderType.getDisplayName());
            createSystemFolder(user, folderType);
        }
        System.out.println("✅ Tous les dossiers système créés");
    }

    /**
     * Initialise les dossiers système pour tous les utilisateurs existants
     */
    @Transactional
    public void initializeSystemFoldersForAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            initializeSystemFolders(user.getId());
        }
    }

    /**
     * Crée un dossier système spécifique
     */
    private void createSystemFolder(User user, SystemFolderType folderType) {
        // Vérifier si ce dossier système existe déjà
        boolean exists = folderRepository.existsByUserAndSystemFolderType(user, folderType);
        if (exists) {
            return;
        }

        // Créer le dossier en base
        Folder systemFolder = new Folder();
        systemFolder.setName(folderType.getDisplayName());
        systemFolder.setPath(folderType.getPath());
        systemFolder.setUser(user);
        systemFolder.setIsSystemFolder(true);
        systemFolder.setSystemFolderType(folderType);
        systemFolder.setIsFavorite(false);
        systemFolder.setParentFolder(null); // Dossiers racine

        folderRepository.save(systemFolder);

        // Créer le dossier physique
        createPhysicalSystemFolder(folderType.getPath());
    }

    /**
     * Vérifie si un dossier est un dossier système
     */
    public boolean isSystemFolder(String path) {
        return Arrays.stream(SystemFolderType.values())
            .anyMatch(type -> type.getPath().equals(path));
    }

    /**
     * Vérifie si l'utilisateur peut modifier/supprimer un dossier
     */
    public boolean canModifyFolder(Long folderId, Long userId) {
        return folderRepository.findById(folderId)
            .map(folder -> !folder.getIsSystemFolder() && folder.getUser().getId().equals(userId))
            .orElse(false);
    }

    /**
     * Vérifie si un type de fichier peut être ajouté dans un dossier système
     */
    public boolean canAddFileToSystemFolder(String folderPath, String contentType) {
        return Arrays.stream(SystemFolderType.values())
            .filter(type -> type.getPath().equals(folderPath))
            .findFirst()
            .map(type -> type.isFileTypeAllowed(contentType))
            .orElse(true); // Si ce n'est pas un dossier système, tout est autorisé
    }

    /**
     * Récupère le type de dossier système à partir du chemin
     */
    public SystemFolderType getSystemFolderType(String path) {
        return Arrays.stream(SystemFolderType.values())
            .filter(type -> type.getPath().equals(path))
            .findFirst()
            .orElse(null);
    }

    /**
     * Crée le dossier physique sur le système de fichiers
     */
    private void createPhysicalSystemFolder(String path) {
        try {
            Path physicalPath = Paths.get(uploadDir, path.substring(1));
            Files.createDirectories(physicalPath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du dossier système physique: " + path, e);
        }
    }

    /**
     * Récupère tous les dossiers système d'un utilisateur
     */
    public List<Folder> getSystemFolders(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return folderRepository.findByUserAndIsSystemFolderTrueOrderByName(user);
    }
}