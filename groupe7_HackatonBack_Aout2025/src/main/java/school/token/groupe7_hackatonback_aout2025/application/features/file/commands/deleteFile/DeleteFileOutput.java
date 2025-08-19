package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile;

public class DeleteFileOutput {

    private String message;

    public DeleteFileOutput() {
    }

    public DeleteFileOutput(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
