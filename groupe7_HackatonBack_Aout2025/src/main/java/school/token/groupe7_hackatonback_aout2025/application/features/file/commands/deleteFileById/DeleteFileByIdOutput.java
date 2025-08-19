package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFileById;

public class DeleteFileByIdOutput {
    private String message;

    public DeleteFileByIdOutput() {
    }

    public DeleteFileByIdOutput(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
