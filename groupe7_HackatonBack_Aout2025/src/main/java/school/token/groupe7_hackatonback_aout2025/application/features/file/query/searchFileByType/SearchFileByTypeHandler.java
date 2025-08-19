package school.token.groupe7_hackatonback_aout2025.application.features.file.query.searchFileByType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class SearchFileByTypeHandler implements IQueryHandler<SearchFileByTypeQuery,SearchFileByTypeOutput> {

    private final FileManagementService fileManagementService;

    public SearchFileByTypeHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public SearchFileByTypeOutput handle(SearchFileByTypeQuery input) {
        try {
            List<FileDto> results = fileManagementService.searchFilesByType(input.getContentType(), input.getUserId());
            return new SearchFileByTypeOutput(results);
        } catch (Exception e) {
            return new SearchFileByTypeOutput(List.of());
        }
    }
}
