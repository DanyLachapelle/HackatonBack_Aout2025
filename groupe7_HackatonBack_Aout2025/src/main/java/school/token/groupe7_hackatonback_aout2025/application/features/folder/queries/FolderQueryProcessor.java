package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder.GetFavoriteFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder.GetFavoriteFolderQuery;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

import java.util.List;

@Service
public class FolderQueryProcessor {

    private final IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> _findFoldersByUserAndPathHandler;
    private final IQueryHandler<GetFavoriteFolderQuery, GetFavoriteFolderOutput> _getFavoriteFoldersHandler;

    public FolderQueryProcessor(IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> findFoldersByUserAndPathHandler, IQueryHandler<GetFavoriteFolderQuery, GetFavoriteFolderOutput> getFavoriteFoldersHandler) {
        _findFoldersByUserAndPathHandler = findFoldersByUserAndPathHandler;
        _getFavoriteFoldersHandler = getFavoriteFoldersHandler;
    }

    public FindFoldersByUserAndPathOutput findFoldersByUserAndPath(FindFoldersByUserAndPathQuery query) {
        return _findFoldersByUserAndPathHandler.handle(query);
    }

    public GetFavoriteFolderOutput getFavoriteFolders(GetFavoriteFolderQuery query) {
        return _getFavoriteFoldersHandler.handle(query);
    }
}
