package school.token.groupe7_hackatonback_aout2025.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFileRequest;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFolderRequest;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.mapper.EntityMapper;
import school.token.groupe7_hackatonback_aout2025.domain.File;
import school.token.groupe7_hackatonback_aout2025.domain.Folder;
import school.token.groupe7_hackatonback_aout2025.domain.User;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FileRepository;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FolderRepository;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.UserRepository;
import school.token.groupe7_hackatonback_aout2025.domain.SystemFolderType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service de gestion des fichiers - Couche Business Logic
 * Respecte l'architecture 4 couches :
 * 1. Controller (Présentation) -> 2. Service (Logique métier) -> 3. Repository (Accès données) -> 4. Model (Entités)
 * Utilise les DTOs pour la communication avec les contrôleurs
 */
@Service
public class FileManagementService {
    
    @Value("${app.upload.dir}")
    private String uploadDir;
    
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final SystemFolderService systemFolderService;
    
    public FileManagementService(FileRepository fileRepository, FolderRepository folderRepository, 
                               UserRepository userRepository, EntityMapper entityMapper,
                               SystemFolderService systemFolderService) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
        this.systemFolderService = systemFolderService;
    }
    
    // === GESTION DES DOSSIERS ===
    
    public List<FolderDto> listFolders(String path, Long userId) {
        User user = getUserById(userId);
        
        System.out.println("🔍 listFolders appelé pour path: " + path + ", userId: " + userId);
        
        // Initialiser les dossiers système si nécessaire
        System.out.println("🗂️ Initialisation des dossiers système...");
        systemFolderService.initializeSystemFolders(userId);
        System.out.println("✅ Initialisation terminée");
        
        List<Folder> folders;
        if ("/".equals(path)) {
            // À la racine, retourner SEULEMENT les dossiers système
            System.out.println("📁 Recherche des dossiers système à la racine...");
            folders = folderRepository.findByUserAndIsSystemFolderTrueOrderByName(user);
            System.out.println("📁 Dossiers système trouvés: " + folders.size());
            folders.forEach(folder -> System.out.println("  - " + folder.getName() + " (" + folder.getPath() + ")"));
        } else {
            Folder parentFolder = folderRepository.findByPathAndUser(path, user)
                .orElseThrow(() -> new RuntimeException("Dossier parent non trouvé"));
            folders = folderRepository.findByUserAndParentFolderOrderByName(user, parentFolder);
        }
        
        List<FolderDto> result = folders.stream()
                .map(entityMapper::toDto)
                .toList();
        
        System.out.println("📤 Retour de " + result.size() + " dossiers");
        return result;
    }
    
    public FolderDto createFolder(CreateFolderRequest request) {
        User user = getUserById(request.getUserId());
        
        // Empêcher la création de dossiers à la racine
        if ("/".equals(request.getParentPath())) {
            throw new RuntimeException("Impossible de créer des dossiers à la racine. Utilisez les dossiers système existants.");
        }
        
        String folderPath = buildPath(request.getParentPath(), request.getName());
        
        if (folderRepository.existsByPathAndUser(folderPath, user)) {
            throw new RuntimeException("Un dossier avec ce nom existe déjà");
        }
        
        Folder parentFolder = folderRepository.findByPathAndUser(request.getParentPath(), user)
            .orElseThrow(() -> new RuntimeException("Dossier parent non trouvé"));
        
        // Vérifier que le dossier parent n'est pas un dossier système racine pour certaines restrictions
        if (parentFolder.getIsSystemFolder() && parentFolder.getSystemFolderType() != SystemFolderType.BUREAU 
            && parentFolder.getSystemFolderType() != SystemFolderType.DOCUMENTS
            && parentFolder.getSystemFolderType() != SystemFolderType.MUSIQUE
            && parentFolder.getSystemFolderType() != SystemFolderType.IMAGES) {
            throw new RuntimeException("Impossible de créer des sous-dossiers dans ce dossier système.");
        }
        
        Folder folder = new Folder(request.getName(), folderPath, user);
        folder.setParentFolder(parentFolder);
        folder.setIsSystemFolder(false); // Les dossiers créés par l'utilisateur ne sont jamais des dossiers système
        
        // Créer le dossier physique
        createPhysicalDirectory(folderPath);
        
        Folder savedFolder = folderRepository.save(folder);
        return entityMapper.toDto(savedFolder);
    }
    
    // === GESTION DES FICHIERS ===
    
    public List<FileDto> listFiles(String path, Long userId) {
        User user = getUserById(userId);
        
        List<File> files;
        if ("/".equals(path)) {
            // À la racine, pas de fichiers directement - ils sont dans les dossiers système
            files = List.of();
        } else {
            Folder folder = folderRepository.findByPathAndUser(path, user)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));
            files = fileRepository.findByUserAndFolderOrderByName(user, folder);
        }
        
        return files.stream()
                .map(entityMapper::toDto)
                .toList();
    }
    
    public FileDto createFile(CreateFileRequest request) {
        User user = getUserById(request.getUserId());
        
        // Empêcher la création de fichiers à la racine
        if ("/".equals(request.getParentPath())) {
            throw new RuntimeException("Impossible de créer des fichiers à la racine. Utilisez les dossiers système.");
        }
        
        String filePath = buildPath(request.getParentPath(), request.getName());
        
        if (fileRepository.existsByPathAndUser(filePath, user)) {
            throw new RuntimeException("Un fichier avec ce nom existe déjà");
        }
        
        Folder folder = folderRepository.findByPathAndUser(request.getParentPath(), user)
            .orElseThrow(() -> new RuntimeException("Dossier parent non trouvé"));
        
        // Vérifier les restrictions des dossiers système
        if (folder.getIsSystemFolder()) {
            String contentType = "text/plain";
            if (!systemFolderService.canAddFileToSystemFolder(request.getParentPath(), contentType)) {
                throw new RuntimeException("Type de fichier non autorisé dans ce dossier système.");
            }
        }
        
        File file = new File(request.getName(), filePath, "text/plain", user);
        file.setFolder(folder);
        file.setContent(request.getContent());
        file.setSize((long) request.getContent().getBytes().length);
        
        // Créer le fichier physique
        String physicalPath = createPhysicalFile(filePath, request.getContent());
        file.setFilePath(physicalPath);
        
        File savedFile = fileRepository.save(file);
        return entityMapper.toDto(savedFile);
    }
    
    public FileDto uploadFile(String parentPath, MultipartFile multipartFile, Long userId) {
        try {
            System.out.println("=== UPLOAD FILE DEBUG ===");
            System.out.println("ParentPath: " + parentPath);
            System.out.println("FileName: " + multipartFile.getOriginalFilename());
            System.out.println("ContentType: " + multipartFile.getContentType());
            System.out.println("Size: " + multipartFile.getSize());
            System.out.println("UserId: " + userId);
            
            User user = getUserById(userId);
            
            String fileName = multipartFile.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("Nom de fichier invalide");
            }
            
            // Si aucun chemin spécifié, utiliser le dossier Bureau par défaut
            if (parentPath == null || parentPath.isEmpty() || "/".equals(parentPath)) {
                Folder bureauFolder = folderRepository.findByUserAndSystemFolderType(user, SystemFolderType.BUREAU)
                    .orElseThrow(() -> new RuntimeException("Dossier Bureau non trouvé"));
                parentPath = bureauFolder.getPath();
            }
            
            String filePath = buildPath(parentPath, fileName);
            System.out.println("FilePath: " + filePath);
            
            if (fileRepository.existsByPathAndUser(filePath, user)) {
                throw new RuntimeException("Un fichier avec ce nom existe déjà");
            }
            
            Folder folder = folderRepository.findByPathAndUser(parentPath, user)
                .orElseThrow(() -> new RuntimeException("Dossier parent non trouvé"));
            
            System.out.println("Folder found: " + folder.getName() + " (System: " + folder.getIsSystemFolder() + ")");
            
            // Vérifier les restrictions des dossiers système
            if (folder.getIsSystemFolder()) {
                String contentType = multipartFile.getContentType();
                System.out.println("Checking system folder restrictions for contentType: " + contentType);
                if (!systemFolderService.canAddFileToSystemFolder(parentPath, contentType)) {
                    System.out.println("❌ File type not allowed in system folder: " + contentType);
                    throw new RuntimeException("Type de fichier non autorisé dans ce dossier système.");
                }
                System.out.println("✅ File type allowed in system folder");
            }
            
            File file = new File(fileName, filePath, multipartFile.getContentType(), user);
            file.setFolder(folder);
            file.setSize(multipartFile.getSize());
            
            // Sauvegarder le fichier physique
            String physicalPath = savePhysicalFile(filePath, multipartFile);
            file.setFilePath(physicalPath);
            
            File savedFile = fileRepository.save(file);
            System.out.println("✅ File uploaded successfully: " + savedFile.getName());
            return entityMapper.toDto(savedFile);
        } catch (Exception e) {
            System.out.println("❌ Error during upload: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public String getFileContent(String path, Long userId) {
        User user = getUserById(userId);
        
        File file = fileRepository.findByPathAndUser(path, user)
            .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        
        if (file.getContent() != null) {
            return file.getContent();
        }
        
        // Lire depuis le fichier physique
        return readPhysicalFile(file.getFilePath());
    }
    
    public void updateFileContent(String path, String content, Long userId) {
        User user = getUserById(userId);
        
        File file = fileRepository.findByPathAndUser(path, user)
            .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        
        file.setContent(content);
        file.setSize((long) content.getBytes().length);
        file.setUpdatedAt(LocalDateTime.now());
        
        // Mettre à jour le fichier physique
        updatePhysicalFile(file.getFilePath(), content);
        
        fileRepository.save(file);
    }
    
    public void deleteFile(String path, Long userId) {
        User user = getUserById(userId);
        
        File file = fileRepository.findByPathAndUser(path, user)
            .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        
        // Supprimer le fichier physique
        deletePhysicalFile(file.getFilePath());
        
        fileRepository.delete(file);
    }

    public void deleteFileById(Long fileId, Long userId) {
        User user = getUserById(userId);
        
        File file = fileRepository.findById(fileId)
            .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        
        // Vérifier que l'utilisateur est propriétaire du fichier
        if (!file.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès non autorisé");
        }
        
        // Supprimer le fichier physique
        deletePhysicalFile(file.getFilePath());
        
        fileRepository.delete(file);
    }

    public void deleteFolderById(Long folderId, Long userId) {
        User user = getUserById(userId);
        
        Folder folder = folderRepository.findById(folderId)
            .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));
        
        // Vérifier que l'utilisateur est propriétaire du dossier
        if (!folder.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès non autorisé");
        }
        
        // Empêcher la suppression des dossiers système
        if (folder.getIsSystemFolder()) {
            throw new RuntimeException("Impossible de supprimer un dossier système");
        }

        List<File> files = fileRepository.findByFolderId(folderId);
        for (File file : files) {
            deletePhysicalFile(file.getPath()); // supprimer le fichier physique
        }
        fileRepository.deleteAll(files);

        // Supprimer le dossier physique
        deletePhysicalFile(folder.getPath());
        
        folderRepository.delete(folder);
    }

    public void renameFile(Long fileId, String newName, Long userId) {
        User user = getUserById(userId);
        
        File file = fileRepository.findById(fileId)
            .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        
        // Vérifier que l'utilisateur est propriétaire du fichier
        if (!file.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès non autorisé");
        }
        
        // Construire le nouveau chemin
        String parentPath = file.getPath().substring(0, file.getPath().lastIndexOf('/'));
        String newPath = buildPath(parentPath, newName);
        
        // Vérifier qu'aucun autre fichier n'a ce nom
        if (fileRepository.existsByPathAndUser(newPath, user)) {
            throw new RuntimeException("Un fichier avec ce nom existe déjà");
        }
        
        // Mettre à jour le nom et le chemin
        file.setName(newName);
        file.setPath(newPath);
        file.setUpdatedAt(LocalDateTime.now());
        
        fileRepository.save(file);
    }

    public void renameFolder(Long folderId, String newName, Long userId) {
        User user = getUserById(userId);
        
        Folder folder = folderRepository.findById(folderId)
            .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));
        
        // Vérifier que l'utilisateur est propriétaire du dossier
        if (!folder.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès non autorisé");
        }
        
        // Empêcher le renommage des dossiers système
        if (folder.getIsSystemFolder()) {
            throw new RuntimeException("Impossible de renommer un dossier système");
        }
        
        // Construire le nouveau chemin
        String parentPath = folder.getPath().substring(0, folder.getPath().lastIndexOf('/'));
        String newPath = buildPath(parentPath, newName);
        
        // Vérifier qu'aucun autre dossier n'a ce nom
        if (folderRepository.existsByPathAndUser(newPath, user)) {
            throw new RuntimeException("Un dossier avec ce nom existe déjà");
        }
        
        // Mettre à jour le nom et le chemin
        folder.setName(newName);
        folder.setPath(newPath);
        folder.setUpdatedAt(LocalDateTime.now());
        
        folderRepository.save(folder);
    }
    
    // === GESTION DES FAVORIS ===
    
    public List<FileDto> getFavoriteFiles(Long userId) {
        User user = getUserById(userId);
        List<File> files = fileRepository.findByUserAndIsFavoriteTrueOrderByName(user);
        return files.stream().map(entityMapper::toDto).toList();
    }
    
    public List<FolderDto> getFavoriteFolders(Long userId) {
        User user = getUserById(userId);
        List<Folder> folders = folderRepository.findByUserAndIsFavoriteTrueOrderByName(user);
        return folders.stream().map(entityMapper::toDto).toList();
    }
    
    public void toggleFileFavorite(String path, Long userId) {
        User user = getUserById(userId);
        
        File file = fileRepository.findByPathAndUser(path, user)
            .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        
        file.setIsFavorite(!file.getIsFavorite());
        fileRepository.save(file);
    }
    
    public void toggleFolderFavorite(String path, Long userId) {
        User user = getUserById(userId);
        
        Folder folder = folderRepository.findByPathAndUser(path, user)
            .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));
        
        folder.setIsFavorite(!folder.getIsFavorite());
        folderRepository.save(folder);
    }
    
    // === RECHERCHE ===
    
    public List<FileDto> searchFiles(String searchTerm, Long userId) {
        User user = getUserById(userId);
        List<File> files = fileRepository.findByUserAndNameContainingIgnoreCaseOrderByName(user, searchTerm);
        return files.stream()
                .map(entityMapper::toDto)
                .toList();
    }
    
    public List<FolderDto> searchFolders(String searchTerm, Long userId) {
        User user = getUserById(userId);
        List<Folder> folders = folderRepository.findByUserAndNameContainingIgnoreCaseOrderByName(user, searchTerm);
        return folders.stream()
                .map(entityMapper::toDto)
                .toList();
    }
    
    public List<FileDto> searchFilesByType(String contentType, Long userId) {
        User user = getUserById(userId);
        List<File> files = fileRepository.findByUserAndContentTypeLikeOrderByUpdatedAtDesc(user, contentType + "%");
        return files.stream().map(entityMapper::toDto).toList();
    }
    
    // === FICHIERS PAR TYPE ===
    
    public List<FileDto> getImageFiles(Long userId) {
        User user = getUserById(userId);
        List<File> files = fileRepository.findByUserAndContentTypeLikeOrderByUpdatedAtDesc(user, "image/%");
        return files.stream().map(entityMapper::toDto).toList();
    }

    public List<FileDto> getAudioFiles(Long userId) {
        User user = getUserById(userId);
        List<File> files = fileRepository.findByUserOrderByUpdatedAtDesc(user);

        return files.stream()
                .filter(file -> file.getContentType() != null &&
                        (file.getContentType().startsWith("audio/") ||
                                file.getContentType().equals("video/mp4")))
                .map(entityMapper::toDto)
                .toList();
    }
    
    public List<FileDto> getTextFiles(Long userId) {
        User user = getUserById(userId);
        List<File> files = fileRepository.findByUserAndContentTypeLikeOrderByUpdatedAtDesc(user, "text/%");
        return files.stream().map(entityMapper::toDto).toList();
    }
    
    // === UTILITAIRES ===
    
    public long getFolderItemCount(String path, Long userId) {
        User user = getUserById(userId);
        
        if ("/".equals(path)) {
            return fileRepository.countByUserAndFolderIsNull(user) + 
                   folderRepository.countByUserAndParentFolderIsNull(user);
        } else {
            Folder folder = folderRepository.findByPathAndUser(path, user)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));
            return fileRepository.countByUserAndFolder(user, folder) + 
                   folderRepository.countByUserAndParentFolder(user, folder);
        }
    }
    
    // === MÉTHODES PRIVÉES ===
    
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
    
    private String buildPath(String parentPath, String name) {
        return "/".equals(parentPath) ? "/" + name : parentPath + "/" + name;
    }
    
    private void createPhysicalDirectory(String path) {
        try {
            Path physicalPath = Paths.get(uploadDir, path.substring(1));
            Files.createDirectories(physicalPath);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la création du dossier physique", e);
        }
    }
    
    private String createPhysicalFile(String path, String content) {
        try {
            Path physicalPath = Paths.get(uploadDir, path.substring(1));
            Files.createDirectories(physicalPath.getParent());
            Files.write(physicalPath, content.getBytes());
            return physicalPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la création du fichier physique", e);
        }
    }
    
    private String savePhysicalFile(String path, MultipartFile file) {
        try {
            Path physicalPath = Paths.get(uploadDir, path.substring(1));
            Files.createDirectories(physicalPath.getParent());
            Files.copy(file.getInputStream(), physicalPath);
            return physicalPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du fichier", e);
        }
    }
    
    private String readPhysicalFile(String filePath) {
        try {
            Path physicalPath = Paths.get(filePath);
            return Files.readString(physicalPath);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }
    
    private void updatePhysicalFile(String filePath, String content) {
        try {
            Path physicalPath = Paths.get(filePath);
            Files.write(physicalPath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du fichier", e);
        }
    }
    
    private void deletePhysicalFile(String filePath) {
        try {
            Path physicalPath = Paths.get(filePath);
            Files.deleteIfExists(physicalPath);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la suppression du fichier physique", e);
        }
    }
}