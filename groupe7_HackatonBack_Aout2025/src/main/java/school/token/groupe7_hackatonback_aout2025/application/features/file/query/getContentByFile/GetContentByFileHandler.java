package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

@Service
public class GetContentByFileHandler implements IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> {

    private final FileManagementService fileManagementService;

    public GetContentByFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }


    @Override
    public GetContentByFileOutput handle(GetContentByFileQuery input) {
        try {
            String content = fileManagementService.getFileContent(input.getPath(), input.getUserId());
            return new GetContentByFileOutput(content);
        } catch (Exception e) {
            return new GetContentByFileOutput(
                "Error retrieving file content: " + e.getMessage()
            );
        }
    }
}
