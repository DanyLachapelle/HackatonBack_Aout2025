package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getTextFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class GetTextFileHandler implements IQueryHandler<GetTextFileQuery,GetTextFileOutput> {
    private final FileManagementService fileManagementService;

    public GetTextFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public GetTextFileOutput handle(GetTextFileQuery input) {
        try {
            List<FileDto> textFiles = fileManagementService.getTextFiles(input.getUserId());
            return new GetTextFileOutput(textFiles);
        } catch (Exception e) {
            return new GetTextFileOutput(List.of());
        }
    }
}
