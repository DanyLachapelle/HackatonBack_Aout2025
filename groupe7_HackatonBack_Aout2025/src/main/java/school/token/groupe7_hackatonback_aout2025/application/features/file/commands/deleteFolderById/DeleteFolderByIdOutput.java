package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFolderById;

public class DeleteFolderByIdOutput {
    private String message;

    public DeleteFolderByIdOutput() {
    }

    public DeleteFolderByIdOutput(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
