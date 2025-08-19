package school.token.groupe7_hackatonback_aout2025.application.features.file.query.dowloadFile;
import org.springframework.core.io.Resource;


public class DowloadFileOutput {
    private final Resource resource;
    private final String filename;
    private final String contentType;
    private final boolean inline;

    public DowloadFileOutput(Resource resource, String filename, String contentType, boolean inline) {
        this.resource = resource;
        this.filename = filename;
        this.contentType = contentType;
        this.inline = inline;
    }

    public Resource getResource() {
        return resource;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    public boolean isInline() {
        return inline;
    }
}
