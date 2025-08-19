package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getImageFile;

public class GetImageFileQuery {
    private Long userId;

    public GetImageFileQuery(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
