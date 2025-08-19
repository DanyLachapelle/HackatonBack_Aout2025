package school.token.groupe7_hackatonback_aout2025.application.features.file.query.countFileByPath;

public class CountFileByPathQuery {
    private String path;
    private Long userId;

    public CountFileByPathQuery(String path, Long userId) {
        this.path = path;
        this.userId = userId;
    }

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
