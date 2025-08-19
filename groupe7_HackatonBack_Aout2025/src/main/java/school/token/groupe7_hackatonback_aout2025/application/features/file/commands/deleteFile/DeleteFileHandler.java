package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;
@Service
public class DeleteFileHandler implements ICommandHandler<DeleteFileCommand,DeleteFileOutput> {

    private final FileManagementService fileManagementService;

    public DeleteFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public DeleteFileOutput handle(DeleteFileCommand input) {
        try {
            fileManagementService.deleteFile(input.getPath(), input.getUserId());
            return new DeleteFileOutput("File deleted successfully");
        } catch (Exception e) {
            return new DeleteFileOutput("Error deleting file: " + e.getMessage());
        }
    }
}
