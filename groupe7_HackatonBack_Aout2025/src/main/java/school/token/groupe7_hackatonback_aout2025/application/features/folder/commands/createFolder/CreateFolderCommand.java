package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder;

public class CreateFolderCommand {
    private String parentPath;
    private String name;
    private Long userId;

    // Constructeurs
    public CreateFolderCommand() {}

    public CreateFolderCommand(String parentPath, String name, Long userId) {
        this.parentPath = parentPath;
        this.name = name;
        this.userId = userId;
    }

    // Getters et Setters

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
