package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile;

import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;

import java.util.List;

public class GetFavoriteFileOutput {
    private List<FileDto> files;

    public GetFavoriteFileOutput(List<FileDto> files) {
        this.files = files;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileDto> files) {
        this.files = files;
    }
}
