package school.token.groupe7_hackatonback_aout2025.application.dto;

public class CreateFolderSecretRequest {
    private String parentPath;
    private String name;
    private Long userId;
    private String password;

    public CreateFolderSecretRequest() {
    }

    public CreateFolderSecretRequest(String parentPath, String name, Long userId, String password) {
        this.parentPath = parentPath;
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
