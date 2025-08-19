package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFolderRequest;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FileRepository;

@Service
public class CreateFolderHandler implements ICommandHandler<CreateFolderCommand, CreateFolderOutput> {

    private final FileRepository fileRepository;
    private final FileManagementService fileManagementService;


    public CreateFolderHandler(FileRepository fileRepository, FileManagementService fileManagementService) {
        this.fileRepository = fileRepository;
        this.fileManagementService = fileManagementService;
    }

    @Override
    public CreateFolderOutput handle(CreateFolderCommand command) {
        CreateFolderRequest request = new CreateFolderRequest(
                command.getParentPath(),
                command.getName(),
                command.getUserId()
        );
        FolderDto folder = fileManagementService.createFolder(request);
        return new CreateFolderOutput(folder);
    }
}
