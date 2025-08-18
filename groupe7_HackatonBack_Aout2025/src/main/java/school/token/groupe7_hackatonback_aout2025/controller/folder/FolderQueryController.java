package school.token.groupe7_hackatonback_aout2025.controller.folder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.FolderQueryProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/folders")
public class FolderQueryController {
    private final FolderQueryProcessor folderQueryProcessor;

    public FolderQueryController(FolderQueryProcessor folderQueryProcessor) {
        this.folderQueryProcessor = folderQueryProcessor;
    }

    @GetMapping("/getFoldersByUserAndPath")
    @ApiResponse(responseCode = "200", description = "Retrieve folders by user and path")
    @ApiResponse(responseCode = "404", description = "Folders not found for the given user and path")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public ResponseEntity<List<FolderDto>> getFoldersByUserAndPath(
            @RequestParam("path") String path,
            @RequestParam("userId") Long userId)
    {

        FindFoldersByUserAndPathOutput output = folderQueryProcessor
                .findFoldersByUserAndPath(new FindFoldersByUserAndPathQuery(path, userId));
        return ResponseEntity.ok(output.getFolders());
    }


}
