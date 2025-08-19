package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.renameFile;

import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.domain.File;

public class RenameFileOutput {
    private File file;

    public RenameFileOutput() {
    }

    public RenameFileOutput(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
}
