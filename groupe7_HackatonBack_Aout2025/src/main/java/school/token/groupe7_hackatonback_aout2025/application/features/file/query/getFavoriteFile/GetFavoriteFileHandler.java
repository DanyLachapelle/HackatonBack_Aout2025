package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class GetFavoriteFileHandler implements IQueryHandler<GetFavoriteFileQuery,GetFavoriteFileOutput> {
    private final FileManagementService fileManagementService;

    public GetFavoriteFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public GetFavoriteFileOutput handle(GetFavoriteFileQuery input) {
        try {
            List<FileDto> favorites = fileManagementService.getFavoriteFiles(input.getUserId());
            return new GetFavoriteFileOutput((List<FileDto>) favorites);
        } catch (Exception e) {
            return new GetFavoriteFileOutput(null);
        }
    }
}
