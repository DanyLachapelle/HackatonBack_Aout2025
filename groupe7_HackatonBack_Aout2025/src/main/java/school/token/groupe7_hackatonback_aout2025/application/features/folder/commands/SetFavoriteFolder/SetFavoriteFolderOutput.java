package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder;

public class SetFavoriteFolderOutput {
    private boolean success;
    private String message;

    public SetFavoriteFolderOutput(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
