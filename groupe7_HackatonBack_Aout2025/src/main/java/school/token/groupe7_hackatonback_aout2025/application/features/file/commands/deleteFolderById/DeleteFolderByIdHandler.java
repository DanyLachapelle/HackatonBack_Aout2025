package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFolderById;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class DeleteFolderByIdHandler implements ICommandHandler<DeleteFolderByIdCommand, DeleteFolderByIdOutput> {

    private final FileManagementService fileManagementService;

    public DeleteFolderByIdHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public DeleteFolderByIdOutput handle(DeleteFolderByIdCommand command) {
        try {
            fileManagementService.deleteFolderById(command.getFolderId(), command.getUserId());
            return new DeleteFolderByIdOutput("Folder deleted successfully");
        } catch (Exception e) {
            return new DeleteFolderByIdOutput("Error deleting folder: " + e.getMessage());
        }
    }
}
