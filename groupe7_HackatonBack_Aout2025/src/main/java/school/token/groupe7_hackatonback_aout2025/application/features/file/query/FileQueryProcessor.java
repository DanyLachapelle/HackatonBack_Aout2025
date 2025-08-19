package school.token.groupe7_hackatonback_aout2025.application.features.file.query;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

@Service
public class FileQueryProcessor {

    private final IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> getFileByPathHandler;
    private final IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> getContentByFileHandler;

    public FileQueryProcessor(IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> getFileByPathHandler, IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> getContentByFileHandler) {
        this.getFileByPathHandler = getFileByPathHandler;
        this.getContentByFileHandler = getContentByFileHandler;
    }

    public GetFileByPathOutput getFileByPath(GetFileByPathQuery query) {
        return getFileByPathHandler.handle(query);
    }

    public GetContentByFileOutput getContentByFile(GetContentByFileQuery query) {
        return getContentByFileHandler.handle(query);
    }
}
