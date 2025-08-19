package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder;

public class SearchFolderQuery {
    private String query;
    private Long userId;

    public SearchFolderQuery(String query, Long userId) {
        this.query = query;
        this.userId = userId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
