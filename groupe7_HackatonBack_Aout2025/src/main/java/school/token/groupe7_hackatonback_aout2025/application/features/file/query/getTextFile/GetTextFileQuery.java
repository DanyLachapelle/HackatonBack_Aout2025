package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getTextFile;

public class GetTextFileQuery {
    private Long userId;

    public GetTextFileQuery(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
