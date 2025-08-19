package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FileRepository;

@Service
public class SetFavoriteFolderHandler implements ICommandHandler<SetFavoriteFolderCommand, SetFavoriteFolderOutput> {

    private final FileRepository fileRepository;
    private final FileManagementService fileManagementService;

    public SetFavoriteFolderHandler(FileRepository fileRepository, FileManagementService fileManagementService) {
        this.fileRepository = fileRepository;
        this.fileManagementService = fileManagementService;
    }

    @Override
    public SetFavoriteFolderOutput handle(SetFavoriteFolderCommand input) {
        try {
            fileManagementService.toggleFolderFavorite(input.getPath(), input.getUserId());

            // tu peux enrichir l'output avec un succès ou l'état actuel du favori
            return new SetFavoriteFolderOutput(true, "Le dossier a été marqué/démarqué comme favori.");
        } catch (Exception e) {
            // ici tu pourrais logger l'erreur et renvoyer un output en échec
            return new SetFavoriteFolderOutput(false, "Erreur : " + e.getMessage());
        }
    }
}
