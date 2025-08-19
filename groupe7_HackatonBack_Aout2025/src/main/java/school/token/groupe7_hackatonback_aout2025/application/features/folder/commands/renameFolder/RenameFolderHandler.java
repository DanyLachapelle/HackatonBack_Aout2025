package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.renameFolder;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;
@Service
public class RenameFolderHandler implements ICommandHandler<RenameFolderCommand,RenameFolderOutput> {
    private final FileManagementService fileManagementService;

    public RenameFolderHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public RenameFolderOutput handle(RenameFolderCommand input) {
        try {
            fileManagementService.renameFolder(input.getFolderId(), input.getNewName(), input.getUserId());
            return new RenameFolderOutput();
        } catch (Exception e) {
            return new RenameFolderOutput();
        }
    }
}
