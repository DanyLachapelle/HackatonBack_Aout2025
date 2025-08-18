package school.token.groupe7_hackatonback_aout2025.application.dto;

public class CreateFileRequest {
    private String parentPath;
    private String name;
    private String content;
    private Long userId;
    
    // Constructeurs
    public CreateFileRequest() {}
    
    public CreateFileRequest(String parentPath, String name, String content, Long userId) {
        this.parentPath = parentPath;
        this.name = name;
        this.content = content;
        this.userId = userId;
    }
    
    // Getters et Setters
    public String getParentPath() {
        return parentPath;
    }
    
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}