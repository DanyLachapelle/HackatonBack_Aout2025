package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFolderById;

public class DeleteFolderByIdCommand {
    private Long folderId;
    private Long userId;

    public DeleteFolderByIdCommand() {
    }

    public DeleteFolderByIdCommand(Long folderId, Long userId) {
        this.folderId = folderId;
        this.userId = userId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
