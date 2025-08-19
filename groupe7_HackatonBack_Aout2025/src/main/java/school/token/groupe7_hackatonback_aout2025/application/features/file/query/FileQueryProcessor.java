package school.token.groupe7_hackatonback_aout2025.application.features.file.query;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.countFileByPath.CountFileByPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.countFileByPath.CountFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getAudioFile.GetAudioFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getAudioFile.GetAudioFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile.GetFavoriteFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile.GetFavoriteFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getImageFile.GetImageFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getImageFile.GetImageFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getTextFile.GetTextFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getTextFile.GetTextFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.searchFileByType.SearchFileByTypeOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.searchFileByType.SearchFileByTypeQuery;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

@Service
public class FileQueryProcessor {

    private final IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> getFileByPathHandler;
    private final IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> getContentByFileHandler;
    private final IQueryHandler<GetFavoriteFileQuery, GetFavoriteFileOutput> getFavoriteFileHandler;
    private final IQueryHandler<SearchFileQuery, SearchFileOutput> searchFileHandler;
    private final IQueryHandler<SearchFileByTypeQuery, SearchFileByTypeOutput> searchFileByTypeHandler;
    private final IQueryHandler<GetImageFileQuery, GetImageFileOutput> getImageFileHandler;
    private final IQueryHandler<GetAudioFileQuery, GetAudioFileOutput> getAudioFileHandler;
    private final IQueryHandler<GetTextFileQuery, GetTextFileOutput> getTextFileHandler;
    private final IQueryHandler<CountFileByPathQuery, CountFileByPathOutput> countFileByPathHandler;
    public FileQueryProcessor(IQueryHandler<GetFileByPathQuery, GetFileByPathOutput> getFileByPathHandler, IQueryHandler<GetContentByFileQuery, GetContentByFileOutput> getContentByFileHandler, IQueryHandler<GetFavoriteFileQuery, GetFavoriteFileOutput> getFavoriteFileHandler, IQueryHandler<SearchFileQuery, SearchFileOutput> searchFileHandler, IQueryHandler<SearchFileByTypeQuery, SearchFileByTypeOutput> searchFileByTypeHandler, IQueryHandler<GetImageFileQuery, GetImageFileOutput> getImageFileHandler, IQueryHandler<GetAudioFileQuery, GetAudioFileOutput> getAudioFileHandler, IQueryHandler<GetTextFileQuery, GetTextFileOutput> getTextFileHandler, IQueryHandler<CountFileByPathQuery, CountFileByPathOutput> countFileByPathHandler) {
        this.getFileByPathHandler = getFileByPathHandler;
        this.getContentByFileHandler = getContentByFileHandler;
        this.getFavoriteFileHandler = getFavoriteFileHandler;
        this.searchFileHandler = searchFileHandler;
        this.searchFileByTypeHandler = searchFileByTypeHandler;
        this.getImageFileHandler = getImageFileHandler;
        this.getAudioFileHandler = getAudioFileHandler;
        this.getTextFileHandler = getTextFileHandler;
        this.countFileByPathHandler = countFileByPathHandler;
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

    public SearchFileByTypeOutput searchFileByType(SearchFileByTypeQuery query) {
        return searchFileByTypeHandler.handle(query);
    }

    public GetImageFileOutput getImageFile(GetImageFileQuery query) {
        return getImageFileHandler.handle(query);
    }

    public GetAudioFileOutput getAudioFile(GetAudioFileQuery query) {
        return getAudioFileHandler.handle(query);
    }

    public GetTextFileOutput getTextFile(GetTextFileQuery query) {
        return getTextFileHandler.handle(query);
    }

    public CountFileByPathOutput countFileByPath(CountFileByPathQuery query) {
        return countFileByPathHandler.handle(query);
    }
}
