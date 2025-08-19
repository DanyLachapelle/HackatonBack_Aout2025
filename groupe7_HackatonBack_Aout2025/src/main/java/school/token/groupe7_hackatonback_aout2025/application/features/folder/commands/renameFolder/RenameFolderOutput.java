package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.renameFolder;

import school.token.groupe7_hackatonback_aout2025.domain.File;

public class RenameFolderOutput {

    private File file;

    public RenameFolderOutput() {
    }

    public RenameFolderOutput(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
