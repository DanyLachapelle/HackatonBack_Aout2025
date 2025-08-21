package school.token.groupe7_hackatonback_aout2025.controller.folder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.FolderSecretDto;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.FolderSecretQueryProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.getFavoriteFolder.GetFavoriteFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.getFavoriteFolder.GetFavoriteFolderQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.searchFolder.SearchFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.searchFolder.SearchFolderQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.verifyAccess.VerifyAccessOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.verifyAccess.VerifyAccessQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.getFolderInfo.GetFolderInfoOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.queries.getFolderInfo.GetFolderInfoQuery;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/folders-secret")
public class FolderSecretQueryController {
    private final FolderSecretQueryProcessor folderSecretQueryProcessor;

    public FolderSecretQueryController(FolderSecretQueryProcessor folderSecretQueryProcessor) {
        this.folderSecretQueryProcessor = folderSecretQueryProcessor;
    }

    @GetMapping("/getFoldersByUserAndPath")
    @ApiResponse(responseCode = "200", description = "Retrieve secret folders by user and path")
    @ApiResponse(responseCode = "404", description = "Secret folders not found for the given user and path")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<FolderSecretDto>> getFoldersByUserAndPath(
            @RequestParam("path") String path,
            @RequestParam("userId") Long userId) {

        FindFoldersByUserAndPathOutput output = folderSecretQueryProcessor
                .findFoldersByUserAndPath(new FindFoldersByUserAndPathQuery(path, userId));
        return ResponseEntity.ok(output.getFolders());
    }

    @GetMapping("/getFavoriteFolders")
    @ApiResponse(responseCode = "200", description = "Retrieve favorite secret folders for the user")
    @ApiResponse(responseCode = "404", description = "No favorite secret folders found for the user")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<FolderSecretDto>> getFavoriteFolders(@RequestParam("userId") Long userId) {
        GetFavoriteFolderQuery query = new GetFavoriteFolderQuery(userId);
        GetFavoriteFolderOutput output = folderSecretQueryProcessor.getFavoriteFolders(query);
        return ResponseEntity.ok(output.getFavoriteFolders());
    }

    @GetMapping("/searchFolders")
    @ApiResponse(responseCode = "200", description = "Search secret folders by query")
    @ApiResponse(responseCode = "404", description = "No secret folders found matching the query")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<FolderSecretDto>> searchFolders(
            @RequestParam("query") String query,
            @RequestParam("userId") Long userId) {

        try {
            SearchFolderQuery searchFolderQuery = new SearchFolderQuery(query, userId);
            SearchFolderOutput output = folderSecretQueryProcessor.searchFolder(searchFolderQuery);

            if (output.getFolders().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(output.getFolders());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la recherche de dossiers secrets : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/verify-access/{folderSecretId}")
    @ApiResponse(responseCode = "200", description = "Access verified successfully")
    @ApiResponse(responseCode = "401", description = "Invalid password")
    @ApiResponse(responseCode = "404", description = "Secret folder not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<VerifyAccessOutput> verifyAccess(
            @PathVariable Long folderSecretId,
            @RequestParam String password,
            @RequestParam Long userId) {
        try {
            VerifyAccessQuery query = new VerifyAccessQuery(folderSecretId, password, userId);
            VerifyAccessOutput output = folderSecretQueryProcessor.verifyAccess(query);
            
            if (output.isAccessGranted()) {
                return ResponseEntity.ok(output);
            } else {
                return ResponseEntity.status(401).body(output);
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la vérification d'accès : " + e.getMessage());
            return ResponseEntity.status(500).body(new VerifyAccessOutput(false, "Erreur interne du serveur"));
        }
    }

    @GetMapping("/getFolderInfo/{folderSecretId}")
    @ApiResponse(responseCode = "200", description = "Secret folder information retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied - password required")
    @ApiResponse(responseCode = "404", description = "Secret folder not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<GetFolderInfoOutput> getFolderInfo(
            @PathVariable Long folderSecretId,
            @RequestParam Long userId) {
        try {
            GetFolderInfoQuery query = new GetFolderInfoQuery(folderSecretId, userId);
            GetFolderInfoOutput output = folderSecretQueryProcessor.getFolderInfo(query);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des infos du dossier secret : " + e.getMessage());
            return ResponseEntity.status(500).body(new GetFolderInfoOutput(null, "Erreur interne du serveur"));
        }
    }
}