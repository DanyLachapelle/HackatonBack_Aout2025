package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile;

public class GetFavoriteFileQuery {
    private Long userId;

    public GetFavoriteFileQuery(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
