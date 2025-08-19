package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.setFavoriteFile;

public class SetFavoriteFileOutput {
    private String path;
    private Long userId;

    public SetFavoriteFileOutput(String path, Long userId) {
        this.path = path;
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public Long getUserId() {
        return userId;
    }


}
