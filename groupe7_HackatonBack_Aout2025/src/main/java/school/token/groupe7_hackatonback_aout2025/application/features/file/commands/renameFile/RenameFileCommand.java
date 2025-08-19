package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.renameFile;

public class RenameFileCommand {
    private Long fileId;
    private String newName;
    private Long userId;

    public RenameFileCommand() {
    }

    public RenameFileCommand(Long fileId, String newName, Long userId) {
        this.fileId = fileId;
        this.newName = newName;
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
