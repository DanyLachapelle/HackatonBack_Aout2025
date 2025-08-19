package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder;

import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;

public class CreateFolderOutput {
    private FolderDto folder;

    public CreateFolderOutput(FolderDto folder) {
        this.folder = folder;
    }

    public FolderDto getFolder() {
        return folder;
    }

    public void setFolder(FolderDto folder) {
        this.folder = folder;
    }


}
