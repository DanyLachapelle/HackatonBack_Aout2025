package school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile;

public class SearchFileQuery {
    private String query;
    private Long userId;

    public SearchFileQuery(String query, Long userId) {
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
