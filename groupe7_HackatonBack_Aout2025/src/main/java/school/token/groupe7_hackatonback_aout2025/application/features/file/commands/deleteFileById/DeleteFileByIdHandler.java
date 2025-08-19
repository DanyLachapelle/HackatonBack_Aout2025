package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFileById;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class DeleteFileByIdHandler implements ICommandHandler<DeleteFileByIdCommand,DeleteFileByIdOutput> {
    private final FileManagementService fileManagementService;

    public DeleteFileByIdHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }


    @Override
    public DeleteFileByIdOutput handle(DeleteFileByIdCommand input) {
        try {
            fileManagementService.deleteFileById(input.getFileId(), input.getUserId());
            return new DeleteFileByIdOutput("File deleted successfully");
        } catch (Exception e) {
            return new DeleteFileByIdOutput("Error deleting file: " + e.getMessage());
        }
    }
}
