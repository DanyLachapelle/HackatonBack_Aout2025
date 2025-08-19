package school.token.groupe7_hackatonback_aout2025.application.features.file.commands.setFavoriteFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class SetFavoriteFileHandler implements ICommandHandler<SetFavoriteFileCommand,SetFavoriteFileOutput> {
    private final FileManagementService fileManagementService;

    public SetFavoriteFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public SetFavoriteFileOutput handle(SetFavoriteFileCommand input) {
        try {
            fileManagementService.toggleFileFavorite(input.getPath(), input.getUserId());
            return new SetFavoriteFileOutput(input.getPath(), input.getUserId());
        } catch (Exception e) {
            return new SetFavoriteFileOutput("Error: " + e.getMessage(), input.getUserId());
        }
    }
}
