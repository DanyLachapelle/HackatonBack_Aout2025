package school.token.groupe7_hackatonback_aout2025.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.token.groupe7_hackatonback_aout2025.dto.*;
import school.token.groupe7_hackatonback_aout2025.service.FileManagementService;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur REST pour la gestion des fichiers - Couche Présentation (Layer 1/4)
 * Architecture 4 couches :
 * 1. Controller (Présentation) - Gère les requêtes HTTP et utilise des DTOs
 * 2. Service (Logique métier) - Contient la logique business
 * 3. Repository (Accès données) - Interface avec la base de données
 * 4. Model (Entités) - Représentation des données
 */
@RestController
@RequestMapping("/api/v2/files")
public class FileManagementController {
    
    private final FileManagementService fileManagementService;
    
    public FileManagementController(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }
    
    // === ENDPOINTS POUR LES DOSSIERS ===
    
    @GetMapping("/folders")
    public ResponseEntity<List<FolderDto>> listFolders(
            @RequestParam String path, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            System.out.println("🎯 GET /folders appelé avec path: " + path + ", userId: " + userId);
            List<FolderDto> folders = fileManagementService.listFolders(path, userId);
            System.out.println("✅ Retour de " + folders.size() + " dossiers");
            return ResponseEntity.ok(folders);
        } catch (Exception e) {
            System.err.println("❌ Erreur dans listFolders: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/folders")
    public ResponseEntity<FolderDto> createFolder(@RequestBody CreateFolderRequest request) {
        try {
            FolderDto folder = fileManagementService.createFolder(request);
            return ResponseEntity.ok(folder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/folders/favorite")
    public ResponseEntity<Void> toggleFolderFavorite(@RequestBody Map<String, Object> request) {
        try {
            String path = (String) request.get("path");
            Long userId = Long.parseLong(request.get("userId").toString());
            fileManagementService.toggleFolderFavorite(path, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/folders/favorites")
    public ResponseEntity<List<FolderDto>> getFavoriteFolders(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FolderDto> favorites = fileManagementService.getFavoriteFolders(userId);
            return ResponseEntity.ok(favorites);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // === ENDPOINTS POUR LES FICHIERS ===
    
    @GetMapping("/files")
    public ResponseEntity<List<FileDto>> listFiles(
            @RequestParam String path, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            System.out.println("🎯 GET /files appelé avec path: " + path + ", userId: " + userId);
            List<FileDto> files = fileManagementService.listFiles(path, userId);
            System.out.println("✅ Retour de " + files.size() + " fichiers");
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            System.err.println("❌ Erreur dans listFiles: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/files")
    public ResponseEntity<FileDto> createFile(@RequestBody CreateFileRequest request) {
        try {
            FileDto file = fileManagementService.createFile(request);
            return ResponseEntity.ok(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // La gestion CORS globale est configurée via WebMvcConfigurer (CorsConfig)

    @PostMapping("/files/upload")
    public ResponseEntity<FileDto> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("parentPath") String parentPath,
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            FileDto uploadedFile = fileManagementService.uploadFile(parentPath, file, userId);
            return ResponseEntity.ok(uploadedFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/files/content")
    public ResponseEntity<String> getFileContent(
            @RequestParam String path, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            String content = fileManagementService.getFileContent(path, userId);
            return ResponseEntity.ok(content);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/files/content")
    public ResponseEntity<Void> updateFileContent(
            @RequestParam String path,
            @RequestParam String content,
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            fileManagementService.updateFileContent(path, content, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/files")
    public ResponseEntity<Void> deleteFile(
            @RequestParam String path, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            fileManagementService.deleteFile(path, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/files/{fileId}")
    public ResponseEntity<Void> deleteFileById(
            @PathVariable Long fileId,
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            fileManagementService.deleteFileById(fileId, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/folders/{folderId}")
    public ResponseEntity<Void> deleteFolderById(
            @PathVariable Long folderId,
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            fileManagementService.deleteFolderById(folderId, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/files/{fileId}/rename")
    public ResponseEntity<Void> renameFile(
            @PathVariable Long fileId,
            @RequestBody Map<String, Object> request) {
        try {
            String newName = (String) request.get("newName");
            Long userId = Long.parseLong(request.get("userId").toString());
            fileManagementService.renameFile(fileId, newName, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/folders/{folderId}/rename")
    public ResponseEntity<Void> renameFolder(
            @PathVariable Long folderId,
            @RequestBody Map<String, Object> request) {
        try {
            String newName = (String) request.get("newName");
            Long userId = Long.parseLong(request.get("userId").toString());
            fileManagementService.renameFolder(folderId, newName, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/files/favorite")
    public ResponseEntity<Void> toggleFileFavorite(@RequestBody Map<String, Object> request) {
        try {
            String path = (String) request.get("path");
            Long userId = Long.parseLong(request.get("userId").toString());
            fileManagementService.toggleFileFavorite(path, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/files/favorites")
    public ResponseEntity<List<FileDto>> getFavoriteFiles(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FileDto> favorites = fileManagementService.getFavoriteFiles(userId);
            return ResponseEntity.ok(favorites);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // === ENDPOINTS DE RECHERCHE ===
    
    @GetMapping("/search")
    public ResponseEntity<List<FileDto>> searchFiles(
            @RequestParam String query, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FileDto> results = fileManagementService.searchFiles(query, userId);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/folders/search")
    public ResponseEntity<List<FolderDto>> searchFolders(
            @RequestParam String query, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FolderDto> results = fileManagementService.searchFolders(query, userId);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/search/type")
    public ResponseEntity<List<FileDto>> searchFilesByType(
            @RequestParam String contentType, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FileDto> results = fileManagementService.searchFilesByType(contentType, userId);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // === ENDPOINTS POUR APPLICATIONS SPÉCIALISÉES ===
    
    @GetMapping("/images")
    public ResponseEntity<List<FileDto>> getImageFiles(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FileDto> images = fileManagementService.getImageFiles(userId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/audio")
    public ResponseEntity<List<FileDto>> getAudioFiles(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FileDto> audioFiles = fileManagementService.getAudioFiles(userId);
            return ResponseEntity.ok(audioFiles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/text")
    public ResponseEntity<List<FileDto>> getTextFiles(@RequestParam(defaultValue = "1") Long userId) {
        try {
            List<FileDto> textFiles = fileManagementService.getTextFiles(userId);
            return ResponseEntity.ok(textFiles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // === ENDPOINTS UTILITAIRES ===
    
    @GetMapping("/count")
    public ResponseEntity<Long> getFolderItemCount(
            @RequestParam String path, 
            @RequestParam(defaultValue = "1") Long userId) {
        try {
            long count = fileManagementService.getFolderItemCount(path, userId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam String path, 
            @RequestParam(defaultValue = "1") Long userId,
            @RequestParam(defaultValue = "false") boolean inline) {
        try {
            // TODO: Implémenter le téléchargement sécurisé via le service
            Path filePath = Paths.get("uploads", path.substring(1));
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                String filename = resource.getFilename();
                String contentType = determineContentType(filename);
                
                ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header("Access-Control-Allow-Origin", "http://localhost:5173")
                        .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                        .header("Access-Control-Allow-Headers", "Range, Accept")
                        .header("Accept-Ranges", "bytes");
                
                if (inline) {
                    // Pour l'affichage inline (images, etc.)
                    responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"");
                } else {
                    // Pour le téléchargement
                    responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
                }
                
                return responseBuilder.body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    private String determineContentType(String filename) {
        if (filename == null) return "application/octet-stream";
        
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            case "bmp":
                return "image/bmp";
            case "svg":
                return "image/svg+xml";
            case "txt":
                return "text/plain";
            case "html":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "json":
                return "application/json";
            case "pdf":
                return "application/pdf";
            // Audio files
            case "mp3":
                return "audio/mpeg";
            case "wav":
                return "audio/wav";
            case "flac":
                return "audio/flac";
            case "ogg":
                return "audio/ogg";
            case "m4a":
                return "audio/mp4";
            case "aac":
                return "audio/aac";
            case "opus":
                return "audio/opus";
            case "wma":
                return "audio/x-ms-wma";
            default:
                return "application/octet-stream";
        }
    }
}