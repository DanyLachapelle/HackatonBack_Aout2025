package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath;

public class GetFileByPathQuery {
    private String path;
    private Long userId;

    // Constructeurs
    public GetFileByPathQuery() {}

    public GetFileByPathQuery(String path, Long userId) {
        this.path = path;
        this.userId = userId;
    }

    // Getters et Setters

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
