package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFileRequest;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class CreateFileHandler implements ICommandHandler<CreateFileCommand, CreateFileOutput> {

    private final FileManagementService fileManagementService;

    public CreateFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public CreateFileOutput handle(CreateFileCommand command) {
        try {
            System.out.println("COMMAND userId = " + command.getUserId());
            CreateFileRequest request = new CreateFileRequest(
                    command.getParentPath(),
                    command.getName(),
                    command.getContent(),
                    command.getUserId()
            );
            System.out.println("COMMAND userId = " + command.getUserId());
            FileDto file = fileManagementService.createFile(request);
            return new CreateFileOutput(file);
        }
        catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Error creating file: " + e.getMessage(), e);
        }
    }
}
