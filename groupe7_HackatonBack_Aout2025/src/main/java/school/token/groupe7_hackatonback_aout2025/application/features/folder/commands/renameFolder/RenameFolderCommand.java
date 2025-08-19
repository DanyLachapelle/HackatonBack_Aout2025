package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.renameFolder;

public class RenameFolderCommand {
    private Long folderId;
    private String newName;
    private Long userId;

    public RenameFolderCommand() {
    }

    public RenameFolderCommand(Long folderId, String newName, Long userId) {
        this.folderId = folderId;
        this.newName = newName;
        this.userId = userId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
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
