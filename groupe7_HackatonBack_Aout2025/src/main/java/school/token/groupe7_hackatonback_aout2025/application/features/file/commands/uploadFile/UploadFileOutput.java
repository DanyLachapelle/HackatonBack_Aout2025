package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile;

import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;

public class UploadFileOutput {
    private FileDto fileDto;

    // Constructeurs
    public UploadFileOutput() {}

    public UploadFileOutput(FileDto fileDto) {
        this.fileDto = fileDto;
    }

    // Getters et Setters
    public FileDto getFileDto() {
        return fileDto;
    }

    public void setFileDto(FileDto fileDto) {
        this.fileDto = fileDto;
    }
}
