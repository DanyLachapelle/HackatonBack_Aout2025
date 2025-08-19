package school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class SearchFileHandler implements IQueryHandler<SearchFileQuery,SearchFileOutput> {
    private final FileManagementService fileManagementService;

    public SearchFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public SearchFileOutput handle(SearchFileQuery input) {
        try {
            List<FileDto> results = fileManagementService.searchFiles(input.getQuery(), input.getUserId());
            return new SearchFileOutput(results);
        } catch (Exception e) {
            return new SearchFileOutput(List.of());
        }
    }
}
