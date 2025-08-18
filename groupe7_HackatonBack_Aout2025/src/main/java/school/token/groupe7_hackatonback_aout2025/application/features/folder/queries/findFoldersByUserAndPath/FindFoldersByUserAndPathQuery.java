package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath;

public class FindFoldersByUserAndPathQuery {
    private String path;
    private long user;

    // Constructeurs
    public FindFoldersByUserAndPathQuery() {}
    public FindFoldersByUserAndPathQuery(String path, long user) {
        this.path = path;
        this.user = user;
    }


    // Getters et Setters
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public long getUser() {
        return user;
    }
    public void setUser(int user) {
        this.user = user;
    }
}
