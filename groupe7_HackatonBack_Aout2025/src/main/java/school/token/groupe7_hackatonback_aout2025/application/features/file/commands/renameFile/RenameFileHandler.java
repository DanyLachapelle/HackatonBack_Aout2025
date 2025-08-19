package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.renameFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class RenameFileHandler implements ICommandHandler<RenameFileCommand,RenameFileOutput> {
    private final FileManagementService fileManagementService;

    public RenameFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public RenameFileOutput handle(RenameFileCommand input) {
        try {
            fileManagementService.renameFile(input.getFileId(), input.getNewName(), input.getUserId());
            return new RenameFileOutput();
        } catch (Exception e) {
            return new RenameFileOutput();
        }
    }
}
