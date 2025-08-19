package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFileById;

public class DeleteFileByIdCommand {
    private Long fileId;
    private Long userId;

    public DeleteFileByIdCommand() {
    }

    public DeleteFileByIdCommand(Long fileId, Long userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
