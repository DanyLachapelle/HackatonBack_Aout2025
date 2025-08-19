package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

import java.lang.reflect.Field;

@Service
public class UploadFileHandler implements ICommandHandler<UploadFileCommand,UploadFileOutput> {

    private final FileManagementService fileManagementService;

    public UploadFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public UploadFileOutput handle(UploadFileCommand input) {
        // Appel du service existant pour upload
        var fileDto = fileManagementService.uploadFile(
                input.getParentPath(),
                input.getMultipartFile(),   // MultipartFile
                input.getUserId()
        );

        // Mapper le résultat vers l'output
        UploadFileOutput output = new UploadFileOutput();
        output.setFileDto(fileDto); // <-- ici on set le résultat

        return output;
    }


}
