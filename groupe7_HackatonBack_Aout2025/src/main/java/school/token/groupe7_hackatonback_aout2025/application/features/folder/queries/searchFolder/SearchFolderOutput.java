package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder;

import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;

import java.util.List;

public class SearchFolderOutput {
    private List<FolderDto> folders;

    public SearchFolderOutput(List<FolderDto> folders) {
        this.folders = folders;
    }

    public List<FolderDto> getFolders() {
        return folders;
    }

    public void setFolders(List<FolderDto> folders) {
        this.folders = folders;
    }

}
