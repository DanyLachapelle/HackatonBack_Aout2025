package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder.GetFavoriteFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder.GetFavoriteFolderQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder.SearchFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder.SearchFolderQuery;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class FolderQueryProcessor {

    private final IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> _findFoldersByUserAndPathHandler;
    private final IQueryHandler<GetFavoriteFolderQuery, GetFavoriteFolderOutput> _getFavoriteFoldersHandler;
    private final IQueryHandler<SearchFolderQuery, SearchFolderOutput> _searchFolderHandler;

    public FolderQueryProcessor(IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> findFoldersByUserAndPathHandler, IQueryHandler<GetFavoriteFolderQuery, GetFavoriteFolderOutput> getFavoriteFoldersHandler, IQueryHandler<SearchFolderQuery, SearchFolderOutput> searchFolderHandler) {
        _findFoldersByUserAndPathHandler = findFoldersByUserAndPathHandler;
        _getFavoriteFoldersHandler = getFavoriteFoldersHandler;
        _searchFolderHandler = searchFolderHandler;
    }

    public FindFoldersByUserAndPathOutput findFoldersByUserAndPath(FindFoldersByUserAndPathQuery query) {
        return _findFoldersByUserAndPathHandler.handle(query);
    }

    public GetFavoriteFolderOutput getFavoriteFolders(GetFavoriteFolderQuery query) {
        return _getFavoriteFoldersHandler.handle(query);
    }

    public SearchFolderOutput searchFolder(SearchFolderQuery query) {
        return _searchFolderHandler.handle(query);
    }
}
