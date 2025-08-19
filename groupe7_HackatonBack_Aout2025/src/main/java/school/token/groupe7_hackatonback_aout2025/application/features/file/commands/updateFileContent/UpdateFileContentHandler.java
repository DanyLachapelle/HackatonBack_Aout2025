package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class UpdateFileContentHandler implements ICommandHandler<UpdateFileContentCommand, UpdateFileContentOutput> {

    private final FileManagementService fileManagementService;

    public UpdateFileContentHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }


    @Override
    public UpdateFileContentOutput handle(UpdateFileContentCommand input) {
        try {
            fileManagementService.updateFileContent(input.getPath(), input.getContent(), input.getUserId());
            return new UpdateFileContentOutput();
        } catch (Exception e) {
            return new UpdateFileContentOutput(e.getMessage());
        }
    }
}
