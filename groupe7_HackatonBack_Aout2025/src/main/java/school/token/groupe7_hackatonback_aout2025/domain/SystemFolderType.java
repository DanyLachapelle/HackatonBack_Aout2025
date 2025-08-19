package school.token.groupe7_hackatonback_aout2025.domain;

/**
 * Types de dossiers système prédéfinis
 */
public enum SystemFolderType {
    BUREAU("Bureau", "🖥️", true),
    MUSIQUE("Musique", "🎵", false),
    IMAGES("Images", "🖼️", false),
    DOCUMENTS("Documents", "📄", false);

    private final String displayName;
    private final String icon;
    private final boolean allowFileUpload;

    SystemFolderType(String displayName, String icon, boolean allowFileUpload) {
        this.displayName = displayName;
        this.icon = icon;
        this.allowFileUpload = allowFileUpload;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isAllowFileUpload() {
        return allowFileUpload;
    }

    public String getPath() {
        return "/" + displayName.toLowerCase();
    }

    /**
     * Vérifie si un type de fichier est autorisé dans ce dossier système
     */
    public boolean isFileTypeAllowed(String contentType) {
        if (contentType == null) return false;
        
        switch (this) {
            case BUREAU:
                return true; // Tous les types de fichiers autorisés sur le bureau
            case MUSIQUE:
                return contentType.startsWith("audio/")  ||
                        contentType.equals("video/mp4");
            case IMAGES:
                return contentType.startsWith("image/");
            case DOCUMENTS:
                return contentType.startsWith("text/") || 
                       contentType.equals("application/pdf") ||
                       contentType.startsWith("application/vnd.openxmlformats-officedocument") ||
                       contentType.startsWith("application/vnd.oasis.opendocument");
            default:
                return false;
        }
    }
}