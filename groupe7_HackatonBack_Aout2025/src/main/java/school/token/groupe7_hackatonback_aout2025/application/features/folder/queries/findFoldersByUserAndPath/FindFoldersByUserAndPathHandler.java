package school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.service.FileManagementService;
import school.token.groupe7_hackatonback_aout2025.application.utils.IQueryHandler;
import school.token.groupe7_hackatonback_aout2025.infrastructure.repository.FileRepository;


import java.util.List;
import java.util.stream.Collectors;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
public class FindFoldersByUserAndPathHandler implements IQueryHandler<FindFoldersByUserAndPathQuery, FindFoldersByUserAndPathOutput> {

    private final FileRepository fileRepository;
    private final FileManagementService fileManagementService;

    public FindFoldersByUserAndPathHandler(FileRepository fileRepository,
                                           FileManagementService fileManagementService) {
        this.fileRepository = fileRepository;
        this.fileManagementService = fileManagementService;
    }




    @Override
    public FindFoldersByUserAndPathOutput handle(FindFoldersByUserAndPathQuery input) {
        List<FolderDto> folders = fileManagementService.listFolders(input.getPath(), input.getUser());
        return new FindFoldersByUserAndPathOutput(folders);
    }
}
