package school.token.groupe7_hackatonback_aout2025.application.features.file.query.searchFileByType;

public class SearchFileByTypeQuery {
    private String contentType;
    private Long userId;

    public SearchFileByTypeQuery(String contentType, Long userId) {
        this.contentType = contentType;
        this.userId = userId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
