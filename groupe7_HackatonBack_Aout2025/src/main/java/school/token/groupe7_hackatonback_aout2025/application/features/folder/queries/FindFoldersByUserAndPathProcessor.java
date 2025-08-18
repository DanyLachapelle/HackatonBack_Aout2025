package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;

@Service
public class FindFoldersByUserAndPathProcessor {

    private final IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> _findFoldersByUserAndPathHandler;


    public FindFoldersByUserAndPathProcessor(IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> findFoldersByUserAndPathHandler) {
        _findFoldersByUserAndPathHandler = findFoldersByUserAndPathHandler;
    }

    public FindFoldersByUserAndPathOutput findFoldersByUserAndPath(FindFoldersByUserAndPathQuery query) {
        return _findFoldersByUserAndPathHandler.handle(query);
    }

}
