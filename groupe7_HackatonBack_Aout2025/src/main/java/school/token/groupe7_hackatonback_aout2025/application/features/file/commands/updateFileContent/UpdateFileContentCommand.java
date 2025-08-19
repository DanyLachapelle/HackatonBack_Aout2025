package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent;

public class UpdateFileContentCommand {
    private String path;
    private String content;
    private Long userId;

    public UpdateFileContentCommand() {
    }

    public UpdateFileContentCommand(String path, String content, Long userId) {
        this.path = path;
        this.content = content;
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
