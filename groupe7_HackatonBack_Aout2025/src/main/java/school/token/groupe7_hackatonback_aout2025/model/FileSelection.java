package school.token.groupe7_hackatonback_aout2025.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_selection")
public class FileSelection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private File file;
    
    @Column(name = "selected_at", nullable = false)
    private LocalDateTime selectedAt;
    
    // Constructeurs
    public FileSelection() {
        this.selectedAt = LocalDateTime.now();
    }
    
    public FileSelection(User user, File file) {
        this();
        this.user = user;
        this.file = file;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public LocalDateTime getSelectedAt() {
        return selectedAt;
    }
    
    public void setSelectedAt(LocalDateTime selectedAt) {
        this.selectedAt = selectedAt;
    }
} 