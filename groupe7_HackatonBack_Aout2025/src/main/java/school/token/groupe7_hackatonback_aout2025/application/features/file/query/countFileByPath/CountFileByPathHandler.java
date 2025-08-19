package school.token.groupe7_hackatonback_aout2025.application.features.file.query.countFileByPath;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;
@Service
public class CountFileByPathHandler implements IQueryHandler<CountFileByPathQuery,CountFileByPathOutput> {
    private final FileManagementService fileManagementService;

    public CountFileByPathHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public CountFileByPathOutput handle(CountFileByPathQuery input) {
        try {
            long count = fileManagementService.getFolderItemCount(input.getPath(), input.getUserId());
            return new CountFileByPathOutput(count);
        } catch (Exception e) {
            return new CountFileByPathOutput(0);
        }
    }
}
