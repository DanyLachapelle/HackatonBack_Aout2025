package school.token.groupe7_hackatonback_aout2025.application.dto;

import java.time.LocalDateTime;

public class FolderDto {
    private Long id;
    private String name;
    private String path;
    private Long parentFolderId;
    private String parentFolderName;
    private Long userId;
    private String username;
    private Boolean isFavorite;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructeurs
    public FolderDto() {}
    
    public FolderDto(Long id, String name, String path, Boolean isFavorite, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.isFavorite = isFavorite;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public Long getParentFolderId() {
        return parentFolderId;
    }
    
    public void setParentFolderId(Long parentFolderId) {
        this.parentFolderId = parentFolderId;
    }
    
    public String getParentFolderName() {
        return parentFolderName;
    }
    
    public void setParentFolderName(String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Boolean getIsFavorite() {
        return isFavorite;
    }
    
    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}