package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getTextFile;

import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;

import java.util.List;

public class GetTextFileOutput {
    private List<FileDto> textFiles;

    public GetTextFileOutput(List<FileDto> textFiles) {
        this.textFiles = textFiles;
    }

    public List<FileDto> getTextFiles() {
        return textFiles;
    }

    public void setTextFiles(List<FileDto> textFiles) {
        this.textFiles = textFiles;
    }
}
