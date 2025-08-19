package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent;

public class UpdateFileContentOutput {

    private String message;
    // Aucune donnée à retourner, juste un message de succès ou d'erreur
    public UpdateFileContentOutput(String message) {
    }

    public UpdateFileContentOutput() {
        this.message = "File content updated successfully";
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
