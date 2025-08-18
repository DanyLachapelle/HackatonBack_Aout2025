package school.token.groupe7_hackatonback_aout2025.controller.folder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFolderRequest;
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

}
