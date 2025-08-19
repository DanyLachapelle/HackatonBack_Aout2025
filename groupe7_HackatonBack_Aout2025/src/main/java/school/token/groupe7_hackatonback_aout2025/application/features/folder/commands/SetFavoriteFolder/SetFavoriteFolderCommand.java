package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder;

public class SetFavoriteFolderCommand {
    private String path;
    private Long userId;

    public SetFavoriteFolderCommand(String path, Long userId) {
        this.path = path;
        this.userId = userId;
    }

    // Getters & Setters
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
