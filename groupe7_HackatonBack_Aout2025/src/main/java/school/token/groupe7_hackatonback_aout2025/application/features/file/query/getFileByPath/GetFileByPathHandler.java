package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class GetFileByPathHandler implements IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> {

    private final FileManagementService fileManagementService;


    public GetFileByPathHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }


    @Override
    public GetFileByPathOutput handle(GetFileByPathQuery input) {
        try {
            List<FileDto> files = fileManagementService.listFiles(input.getPath(), input.getUserId());
            System.out.println("✅ Retour de " + files.size() + " fichiers");
            return new GetFileByPathOutput( files);
        }
        catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des fichiers : " + e.getMessage());
            return new GetFileByPathOutput((List<FileDto>) ResponseEntity.badRequest().body(e.getMessage()));
        }
    }
}
