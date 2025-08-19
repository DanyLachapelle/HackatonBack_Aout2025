package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder;

import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;

import java.util.List;

public class GetFavoriteFolderOutput {
    private List<FolderDto> favoriteFolders;

    public GetFavoriteFolderOutput(List<FolderDto> favoriteFolders) {
        this.favoriteFolders = favoriteFolders;
    }

    public List<FolderDto> getFavoriteFolders() {
        return favoriteFolders;
    }

    public void setFavoriteFolders(List<FolderDto> favoriteFolders) {
        this.favoriteFolders = favoriteFolders;
    }
}
