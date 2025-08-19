package school.token.groupe7_hackatonback_aout2025.application.features.file.query.dowloadFile;

public class DowloadFileQuery {
    private final String path;
    private final Long userId;
    private final boolean inline;

    public DowloadFileQuery(String path, Long userId, boolean inline) {
        this.path = path;
        this.userId = userId;
        this.inline = inline;
    }

    public String getPath() {
        return path;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isInline() {
        return inline;
    }

}
