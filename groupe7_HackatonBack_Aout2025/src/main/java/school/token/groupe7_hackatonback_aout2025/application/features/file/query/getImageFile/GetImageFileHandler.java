package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getImageFile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class GetImageFileHandler implements IQueryHandler<GetImageFileQuery,GetImageFileOutput> {
    private final FileManagementService fileManagementService;

    public GetImageFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public GetImageFileOutput handle(GetImageFileQuery input) {
        try {
            List<FileDto> images = fileManagementService.getImageFiles(input.getUserId());
            return new GetImageFileOutput(images);
        } catch (Exception e) {
            return new GetImageFileOutput(List.of());
        }
    }
}
