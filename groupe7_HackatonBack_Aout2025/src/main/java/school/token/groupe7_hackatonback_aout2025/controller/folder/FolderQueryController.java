package school.token.groupe7_hackatonback_aout2025.controller.folder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderDto;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.FolderQueryProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder.GetFavoriteFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.getFavoriteFolder.GetFavoriteFolderQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder.SearchFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.searchFolder.SearchFolderQuery;

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

    @GetMapping("/getFavoriteFolders")
    @ApiResponse(responseCode = "200", description = "Retrieve favorite folders for the user")
    @ApiResponse(responseCode = "404", description = "No favorite folders found for the user")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<FolderDto>> getFavoriteFolders(@RequestParam("userId") Long userId) {
        GetFavoriteFolderQuery query = new GetFavoriteFolderQuery(userId);
        GetFavoriteFolderOutput output = folderQueryProcessor.getFavoriteFolders(query);

        return ResponseEntity.ok(output.getFavoriteFolders());
    }

    @GetMapping("/searchFolders")
    @ApiResponse(responseCode = "200", description = "Search folders by query")
    @ApiResponse(responseCode = "404", description = "No folders found matching the query")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<FolderDto>> searchFolders(
            @RequestParam("query") String query,
            @RequestParam("userId") Long userId)
    {

        try {
            SearchFolderQuery searchFolderQuery = new SearchFolderQuery(query, userId);
            SearchFolderOutput output = folderQueryProcessor.searchFolder(searchFolderQuery);

            if (output.getFolders().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(output.getFolders());
        }catch (Exception e) {
            System.out.println("❌ Erreur lors de la recherche de dossiers : " + e.getMessage());
            return ResponseEntity.status(500).body(null); // Retourne une réponse 500 en cas d'erreur
        }

    }


}
