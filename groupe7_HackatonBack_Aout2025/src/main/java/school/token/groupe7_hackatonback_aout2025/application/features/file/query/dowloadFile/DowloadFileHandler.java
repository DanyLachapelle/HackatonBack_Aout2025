package school.token.groupe7_hackatonback_aout2025.application.features.file.query.dowloadFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DowloadFileHandler implements IQueryHandler<DowloadFileQuery,DowloadFileOutput> {
    private final FileManagementService fileManagementService;

    public DowloadFileHandler(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @Override
    public DowloadFileOutput handle(DowloadFileQuery input) {
        try {
            Path filePath = Paths.get("uploads", input.getPath().substring(1));
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String filename = resource.getFilename();
                String contentType = determineContentType(filename);

                return new DowloadFileOutput(resource, filename, contentType, input.isInline());
            } else {
                throw new RuntimeException("File not found: " + input.getPath());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid file path: " + input.getPath(), e);
        }
    }



    private String determineContentType(String filename) {
        if (filename == null) return "application/octet-stream";

        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            case "bmp":
                return "image/bmp";
            case "svg":
                return "image/svg+xml";
            case "txt":
                return "text/plain";
            case "html":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "json":
                return "application/json";
            case "pdf":
                return "application/pdf";
            // Audio files
            case "mp3":
                return "audio/mpeg";
            case "wav":
                return "audio/wav";
            case "flac":
                return "audio/flac";
            case "ogg":
                return "audio/ogg";
            case "m4a":
                return "audio/mp4";
            case "aac":
                return "audio/aac";
            case "opus":
                return "audio/opus";
            case "wma":
                return "audio/x-ms-wma";
            default:
                return "application/octet-stream";
        }
    }


}
