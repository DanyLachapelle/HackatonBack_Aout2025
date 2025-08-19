package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class SearchFolderHandler implements IQueryHandler<SearchFolderQuery,SearchFolderOutput> {

    private final FileManagementService fileManagementService;

    public SearchFolderHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public SearchFolderOutput handle(SearchFolderQuery input) {
        try {
            List<FolderDto> results = fileManagementService.searchFolders(input.getQuery(), input.getUserId());
            return new SearchFolderOutput(results);
        } catch (Exception e) {
            return new SearchFolderOutput(List.of());
        }
    }
}
