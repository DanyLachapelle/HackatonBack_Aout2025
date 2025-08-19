package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getAudioFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class GetAudioFileHandler implements IQueryHandler<GetAudioFileQuery,GetAudioFileOutput> {
    private final FileManagementService fileManagementService;

    public GetAudioFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public GetAudioFileOutput handle(GetAudioFileQuery input) {
        try {
            List<FileDto> audioFiles = fileManagementService.getAudioFiles(input.getUserId());
            return new GetAudioFileOutput(audioFiles);
        } catch (Exception e) {
            return new GetAudioFileOutput(List.of());
        }
    }
}
