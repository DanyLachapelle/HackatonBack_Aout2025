package school.token.groupe7_hackatonback_aout2025.application.dto.foldersecret;

public class CreateFolderSecretRequest {
    private String parentPath;
    private String name;
    private Long userId;
    private String password;

    public CreateFolderSecretRequest() {}
    public CreateFolderSecretRequest(String parentPath, String name, Long userId, String password) {
        this.parentPath = parentPath;
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    public String getParentPath() { return parentPath; }
    public void setParentPath(String parentPath) { this.parentPath = parentPath; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

public class SetFavoriteFolderSecretRequest {
    private String path;
    private Long userId;

    public SetFavoriteFolderSecretRequest() {}
    public SetFavoriteFolderSecretRequest(String path, Long userId) {
        this.path = path;
        this.userId = userId;
    }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

public class RenameFolderSecretRequest {
    private String newName;
    private Long userId;

    public RenameFolderSecretRequest() {}
    public RenameFolderSecretRequest(String newName, Long userId) {
        this.newName = newName;
        this.userId = userId;
    }

    public String getNewName() { return newName; }
    public void setNewName(String newName) { this.newName = newName; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

public class VerifyPasswordRequest {
    private String password;
    private Long userId;

    public VerifyPasswordRequest() {}
    public VerifyPasswordRequest(String password, Long userId) {
        this.password = password;
        this.userId = userId;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

public class UpdateFolderSecretPasswordRequest {
    private String currentPassword;
    private String newPassword;
    private Long userId;

    public UpdateFolderSecretPasswordRequest() {}
    public UpdateFolderSecretPasswordRequest(String currentPassword, String newPassword, Long userId) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.userId = userId;
    }

    public String getCurrentPassword() { return currentPassword; }
    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}