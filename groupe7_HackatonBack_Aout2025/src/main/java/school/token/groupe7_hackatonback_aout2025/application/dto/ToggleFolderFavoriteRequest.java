package school.token.groupe7_hackatonback_aout2025.application.dto;

public class ToggleFolderFavoriteRequest {
    private String path;
    private Long userId;

    // Getters & Setters
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
