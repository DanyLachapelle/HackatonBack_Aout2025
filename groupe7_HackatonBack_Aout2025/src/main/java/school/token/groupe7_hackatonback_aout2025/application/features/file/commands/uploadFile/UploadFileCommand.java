package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileCommand {
    private String parentPath;
    private Long userId;
    private MultipartFile multipartFile;

    // Constructeurs
    public UploadFileCommand() {}
    public UploadFileCommand(String parentPath, Long userId, MultipartFile multipartFile) {
        this.parentPath = parentPath;
        this.userId = userId;
        this.multipartFile = multipartFile;
    }

    // Getters et Setters
    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
