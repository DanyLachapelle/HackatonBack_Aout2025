package school.token.groupe7_hackatonback_aout2025.application.dto;

public class RenameFolderSecretRequest {
    private String newName;
    private Long userId;

    public RenameFolderSecretRequest() {
    }

    public RenameFolderSecretRequest(String newName, Long userId) {
        this.newName = newName;
        this.userId = userId;
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
