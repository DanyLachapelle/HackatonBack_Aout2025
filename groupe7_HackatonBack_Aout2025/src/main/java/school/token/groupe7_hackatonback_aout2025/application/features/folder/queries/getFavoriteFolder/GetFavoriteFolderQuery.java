package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder;

public class GetFavoriteFolderQuery {
    private final long userId;

    public GetFavoriteFolderQuery(long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
