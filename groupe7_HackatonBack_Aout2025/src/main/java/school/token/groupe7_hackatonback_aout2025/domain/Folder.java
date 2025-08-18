package school.token.groupe7_hackatonback_aout2025.domain;

import jakarta.persistence.*;
import school.token.groupe7_hackatonback_aout2025.model.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "folder")
public class Folder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(unique = true, nullable = false, length = 500)
    private String path;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_folder_id")
    private Folder parentFolder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "is_favorite")
    private Boolean isFavorite = false;
    
    @Column(name = "is_system_folder", nullable = false)
    private Boolean isSystemFolder = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "system_folder_type")
    private SystemFolderType systemFolderType;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    // Constructeurs
    public Folder() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Folder(String name, String path, User user) {
        this();
        this.name = name;
        this.path = path;
        this.user = user;
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
    
    public Folder getParentFolder() {
        return parentFolder;
    }
    
    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Boolean getIsFavorite() {
        return isFavorite;
    }
    
    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
    
    public Boolean getIsSystemFolder() {
        return isSystemFolder;
    }
    
    public void setIsSystemFolder(Boolean isSystemFolder) {
        this.isSystemFolder = isSystemFolder;
    }
    
    public SystemFolderType getSystemFolderType() {
        return systemFolderType;
    }
    
    public void setSystemFolderType(SystemFolderType systemFolderType) {
        this.systemFolderType = systemFolderType;
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