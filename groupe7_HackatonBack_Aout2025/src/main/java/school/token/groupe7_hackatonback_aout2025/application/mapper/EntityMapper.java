package school.token.groupe7_hackatonback_aout2025.application.mapper;

import org.springframework.stereotype.Component;
import school.token.groupe7_hackatonback_aout2025.application.dto.CalendarEventDto;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.dto.UserDto;
import school.token.groupe7_hackatonback_aout2025.domain.*;
import school.token.groupe7_hackatonback_aout2025.model.CalendarEvent;
import school.token.groupe7_hackatonback_aout2025.model.User;

/**
 * Mapper pour convertir entre les entités et les DTOs
 * Respecte l'architecture 4 couches en séparant les données de transport des entités métier
 */
@Component
public class EntityMapper {
    
    // === USER MAPPINGS ===
    
    public UserDto toDto(User user) {
        if (user == null) return null;
        
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getCreatedAt()
        );
    }
    
    public User toEntity(UserDto dto) {
        if (dto == null) return null;
        
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(dto.getCreatedAt());
        return user;
    }
    
    // === FOLDER MAPPINGS ===
    
    public FolderDto toDto(Folder folder) {
        if (folder == null) return null;
        
        FolderDto dto = new FolderDto(
            folder.getId(),
            folder.getName(),
            folder.getPath(),
            folder.getIsFavorite(),
            folder.getCreatedAt(),
            folder.getUpdatedAt()
        );
        
        // Mapper les relations
        if (folder.getParentFolder() != null) {
            dto.setParentFolderId(folder.getParentFolder().getId());
            dto.setParentFolderName(folder.getParentFolder().getName());
        }
        
        if (folder.getUser() != null) {
            dto.setUserId(folder.getUser().getId());
            dto.setUsername(folder.getUser().getUsername());
        }
        
        return dto;
    }
    
    public Folder toEntity(FolderDto dto) {
        if (dto == null) return null;
        
        Folder folder = new Folder();
        folder.setId(dto.getId());
        folder.setName(dto.getName());
        folder.setPath(dto.getPath());
        folder.setIsFavorite(dto.getIsFavorite());
        folder.setCreatedAt(dto.getCreatedAt());
        folder.setUpdatedAt(dto.getUpdatedAt());
        
        // Note: Les relations sont gérées par le service
        return folder;
    }
    
    // === FILE MAPPINGS ===
    
    public FileDto toDto(File file) {
        if (file == null) return null;
        
        FileDto dto = new FileDto(
            file.getId(),
            file.getName(),
            file.getPath(),
            file.getContentType(),
            file.getSize(),
            file.getIsFavorite(),
            file.getCreatedAt(),
            file.getUpdatedAt()
        );
        
        // Mapper les relations
        if (file.getFolder() != null) {
            dto.setFolderId(file.getFolder().getId());
            dto.setFolderName(file.getFolder().getName());
            dto.setFolderPath(file.getFolder().getPath());
        }
        
        if (file.getUser() != null) {
            dto.setUserId(file.getUser().getId());
            dto.setUsername(file.getUser().getUsername());
        }
        
        return dto;
    }
    
    public File toEntity(FileDto dto) {
        if (dto == null) return null;
        
        File file = new File();
        file.setId(dto.getId());
        file.setName(dto.getName());
        file.setPath(dto.getPath());
        file.setContentType(dto.getContentType());
        file.setSize(dto.getSize());
        file.setIsFavorite(dto.getIsFavorite());
        file.setCreatedAt(dto.getCreatedAt());
        file.setUpdatedAt(dto.getUpdatedAt());
        
        // Note: Les relations sont gérées par le service
        return file;
    }
    
    // === CALENDAR EVENT MAPPINGS ===
    
    public CalendarEventDto toDto(CalendarEvent event) {
        if (event == null) return null;
        
        CalendarEventDto dto = new CalendarEventDto(
            event.getId(),
            event.getTitle(),
            event.getStartDate(),
            event.getEndDate(),
            event.getColor(),
            event.getIsAllDay(),
            event.getLocation()
        );
        
        dto.setDescription(event.getDescription());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        
        // Mapper les relations
        if (event.getUser() != null) {
            dto.setUserId(event.getUser().getId());
            dto.setUsername(event.getUser().getUsername());
        }
        
        return dto;
    }
    
    public CalendarEvent toEntity(CalendarEventDto dto) {
        if (dto == null) return null;
        
        CalendarEvent event = new CalendarEvent();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());
        event.setColor(dto.getColor());
        event.setIsAllDay(dto.getIsAllDay());
        event.setLocation(dto.getLocation());
        event.setCreatedAt(dto.getCreatedAt());
        event.setUpdatedAt(dto.getUpdatedAt());
        
        // Note: Les relations sont gérées par le service
        return event;
    }
}