package school.token.groupe7_hackatonback_aout2025.application.features.file.query;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile.GetFavoriteFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile.GetFavoriteFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

@Service
public class FileQueryProcessor {

    private final IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> getFileByPathHandler;
    private final IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> getContentByFileHandler;
    private final IQueryHandler<GetFavoriteFileQuery, GetFavoriteFileOutput> getFavoriteFileHandler;
    private final IQueryHandler<SearchFileQuery, SearchFileOutput> searchFileHandler;

    public FileQueryProcessor(IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> getFileByPathHandler, IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> getContentByFileHandler, IQueryHandler<GetFavoriteFileQuery, GetFavoriteFileOutput> getFavoriteFileHandler, IQueryHandler<SearchFileQuery, SearchFileOutput> searchFileHandler) {
        this.getFileByPathHandler = getFileByPathHandler;
        this.getContentByFileHandler = getContentByFileHandler;
        this.getFavoriteFileHandler = getFavoriteFileHandler;
        this.searchFileHandler = searchFileHandler;
    }

    public GetFileByPathOutput getFileByPath(GetFileByPathQuery query) {
        return getFileByPathHandler.handle(query);
    }

    public GetContentByFileOutput getContentByFile(GetContentByFileQuery query) {
        return getContentByFileHandler.handle(query);
    }

    public GetFavoriteFileOutput getFavoriteFile(GetFavoriteFileQuery query) {
        return getFavoriteFileHandler.handle(query);
    }

    public SearchFileOutput searchFile(SearchFileQuery query) {
        return searchFileHandler.handle(query);
    }
}
