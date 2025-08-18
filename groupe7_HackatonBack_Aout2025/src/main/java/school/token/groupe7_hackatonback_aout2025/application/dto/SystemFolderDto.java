package school.token.groupe7_hackatonback_aout2025.application.dto;

/**
 * DTO pour les informations des dossiers système
 */
public class SystemFolderDto {
    private String name;
    private String path;
    private String icon;
    private boolean allowFileUpload;
    private boolean allowSubfolders;

    public SystemFolderDto() {}

    public SystemFolderDto(String name, String path, String icon, boolean allowFileUpload, boolean allowSubfolders) {
        this.name = name;
        this.path = path;
        this.icon = icon;
        this.allowFileUpload = allowFileUpload;
        this.allowSubfolders = allowSubfolders;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isAllowFileUpload() {
        return allowFileUpload;
    }

    public void setAllowFileUpload(boolean allowFileUpload) {
        this.allowFileUpload = allowFileUpload;
    }

    public boolean isAllowSubfolders() {
        return allowSubfolders;
    }

    public void setAllowSubfolders(boolean allowSubfolders) {
        this.allowSubfolders = allowSubfolders;
    }
}