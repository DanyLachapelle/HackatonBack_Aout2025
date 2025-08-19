package school.token.groupe7_hackatonback_aout2025.controller.folder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFolderRequest;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.renameFolder.RenameFolderCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.FolderCommandProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder.SetFavoriteFolderCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder.SetFavoriteFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder.CreateFolderCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder.CreateFolderOutput;

@CrossOrigin
@RestController
@RequestMapping("/api/folders")
public class FolderCommandController {
    private final FolderCommandProcessor folderCommandProcessor;

    public FolderCommandController(FolderCommandProcessor folderCommandProcessor) {
        this.folderCommandProcessor = folderCommandProcessor;
    }

    @PostMapping("/CreateFolder")
    @ApiResponse(responseCode = "200", description = "Folder created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public CreateFolderOutput createFolder(@RequestBody CreateFolderRequest request) {
        CreateFolderCommand command = new CreateFolderCommand(
                request.getParentPath(),
                request.getName(),
                request.getUserId()
        );
        return folderCommandProcessor.createFolder(command);
    }

    @PostMapping("/SetFavoriteFolder")
    @ApiResponse(responseCode = "200", description = "Folder favorite status updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public SetFavoriteFolderOutput setFavoriteFolder(@RequestBody SetFavoriteFolderCommand input) {
        SetFavoriteFolderCommand command = new SetFavoriteFolderCommand(
                input.getPath(),
                input.getUserId()
        );
        return folderCommandProcessor.setFavoriteFolder(command);
    }

    @PutMapping("/RenameFolder/{folderId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Folder renamed successfully"),
            @ApiResponse(responseCode = "404", description = "Folder not found for the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> renameFolder(@PathVariable Long folderId,
                                               @RequestParam String newName,
                                               @RequestParam(defaultValue = "1") Long userId) {
        try {
            RenameFolderCommand folderCommand   = new RenameFolderCommand(folderId, newName, userId);
            folderCommandProcessor.renameFolder(folderCommand);
            return ResponseEntity.ok("Folder renamed successfully");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du renommage du dossier : " + e.getMessage());
            return ResponseEntity.status(500).body("Error renaming folder: " + e.getMessage());
        }
    }
}
