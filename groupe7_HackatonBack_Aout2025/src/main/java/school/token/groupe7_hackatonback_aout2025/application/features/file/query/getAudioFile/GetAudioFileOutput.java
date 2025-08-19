package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getAudioFile;

import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;

import java.util.List;

public class GetAudioFileOutput {
    private List<FileDto> audioFiles;

    public GetAudioFileOutput(List<FileDto> audioFiles) {
        this.audioFiles = audioFiles;
    }

    public List<FileDto> getAudioFiles() {
        return audioFiles;
    }

    public void setAudioFiles(List<FileDto> audioFiles) {
        this.audioFiles = audioFiles;
    }

}
