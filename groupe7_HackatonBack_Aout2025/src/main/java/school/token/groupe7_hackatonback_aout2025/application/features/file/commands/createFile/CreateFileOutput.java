package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile;

import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;

public class CreateFileOutput {
    private FileDto file;

    // Constructeurs
    public CreateFileOutput() {}

    public CreateFileOutput(FileDto file) {
        this.file = file;
    }

    // Getters et Setters
    public FileDto getFile() {
        return file;
    }

    public void setFile(FileDto file) {
        this.file = file;
    }


}
