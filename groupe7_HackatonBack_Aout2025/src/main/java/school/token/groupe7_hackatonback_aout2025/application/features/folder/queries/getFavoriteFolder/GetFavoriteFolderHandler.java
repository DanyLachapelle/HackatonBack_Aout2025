package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FileRepository;

import java.util.List;

@Service
public class GetFavoriteFolderHandler implements IQueryHandler<GetFavoriteFolderQuery, GetFavoriteFolderOutput> {

    private final FileRepository fileRepository;
    private final FileManagementService fileManagementService;

    public GetFavoriteFolderHandler(FileRepository fileRepository, FileManagementService fileManagementService) {
        this.fileRepository = fileRepository;
        this.fileManagementService = fileManagementService;
    }

    public GetFavoriteFolderOutput handle(GetFavoriteFolderQuery input) {
        try {
            List<FolderDto> favorites = fileManagementService.getFavoriteFolders(input.getUserId());
            return new GetFavoriteFolderOutput(favorites);
        }
        catch (Exception e) {
            // Log the exception (not shown here for brevity)
            return new GetFavoriteFolderOutput(List.of()); // Return an empty list in case of error
        }
    }
}
