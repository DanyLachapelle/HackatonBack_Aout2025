package school.token.groupe7_hackatonback_aout2025.controller.folder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.foldersecret.*;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.FolderSecretCommandProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.createFolderSecret.CreateFolderSecretCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.createFolderSecret.CreateFolderSecretOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.deleteFolderSecret.DeleteFolderSecretCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.deleteFolderSecret.DeleteFolderSecretOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.renameFolderSecret.RenameFolderSecretCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.renameFolderSecret.RenameFolderSecretOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.SetFavoriteFolderSecret.SetFavoriteFolderSecretCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.SetFavoriteFolderSecret.SetFavoriteFolderSecretOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.updatePassword.UpdateFolderSecretPasswordCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.updatePassword.UpdateFolderSecretPasswordOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.verifyPassword.VerifyFolderSecretPasswordCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.foldersecret.commands.verifyPassword.VerifyFolderSecretPasswordOutput;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/folders-secret")
@Tag(name = "Folder Secret Management", description = "APIs for managing secret folders with password protection")
public class FolderSecretCommandController {
    
    private final FolderSecretCommandProcessor folderSecretCommandProcessor;

    public FolderSecretCommandController(FolderSecretCommandProcessor folderSecretCommandProcessor) {
        this.folderSecretCommandProcessor = folderSecretCommandProcessor;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new secret folder", description = "Creates a new password-protected folder")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Secret folder created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "409", description = "Folder already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CreateFolderSecretOutput> createFolderSecret(@RequestBody CreateFolderSecretRequest request) {
        try {
            CreateFolderSecretCommand command = new CreateFolderSecretCommand(
                request.getParentPath(),
                request.getName(),
                request.getUserId(),
                request.getPassword()
            );
            CreateFolderSecretOutput output = folderSecretCommandProcessor.createFolderSecret(command);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la création du dossier secret : " + e.getMessage());
            return ResponseEntity.status(500).body(new CreateFolderSecretOutput(false, "Erreur interne du serveur", null));
        }
    }

    @PostMapping("/set-favorite")
    @Operation(summary = "Toggle favorite status", description = "Mark or unmark a secret folder as favorite")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Favorite status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Folder not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<SetFavoriteFolderSecretOutput> setFavoriteFolderSecret(@RequestBody SetFavoriteFolderSecretRequest request) {
        try {
            SetFavoriteFolderSecretCommand command = new SetFavoriteFolderSecretCommand(
                request.getPath(),
                request.getUserId()
            );
            SetFavoriteFolderSecretOutput output = folderSecretCommandProcessor.setFavoriteFolderSecret(command);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la modification du statut favori : " + e.getMessage());
            return ResponseEntity.status(500).body(new SetFavoriteFolderSecretOutput(false, "Erreur interne du serveur"));
        }
    }

    @PutMapping("/rename/{folderSecretId}")
    @Operation(summary = "Rename secret folder", description = "Rename an existing secret folder")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Secret folder renamed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid new name"),
        @ApiResponse(responseCode = "404", description = "Secret folder not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RenameFolderSecretOutput> renameFolderSecret(
            @PathVariable Long folderSecretId,
            @RequestBody RenameFolderSecretRequest request) {
        try {
            RenameFolderSecretCommand command = new RenameFolderSecretCommand(
                folderSecretId,
                request.getNewName(),
                request.getUserId()
            );
            RenameFolderSecretOutput output = folderSecretCommandProcessor.renameFolderSecret(command);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du renommage du dossier secret : " + e.getMessage());
            return ResponseEntity.status(500).body(new RenameFolderSecretOutput(false, "Erreur interne du serveur"));
        }
    }

    @PostMapping("/verify-password/{folderSecretId}")
    @Operation(summary = "Verify folder password", description = "Verify if the provided password matches the folder's password")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Password verification successful"),
        @ApiResponse(responseCode = "401", description = "Invalid password"),
        @ApiResponse(responseCode = "404", description = "Secret folder not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<VerifyFolderSecretPasswordOutput> verifyPassword(
            @PathVariable Long folderSecretId,
            @RequestBody VerifyPasswordRequest request) {
        try {
            VerifyFolderSecretPasswordCommand command = new VerifyFolderSecretPasswordCommand(
                folderSecretId,
                request.getPassword(),
                request.getUserId()
            );
            VerifyFolderSecretPasswordOutput output = folderSecretCommandProcessor.verifyPassword(command);
            
            if (output.isPasswordValid()) {
                return ResponseEntity.ok(output);
            } else {
                return ResponseEntity.status(401).body(output);
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la vérification du mot de passe : " + e.getMessage());
            return ResponseEntity.status(500).body(new VerifyFolderSecretPasswordOutput(false, "Erreur interne du serveur"));
        }
    }

    @PutMapping("/update-password/{folderSecretId}")
    @Operation(summary = "Update folder password", description = "Change the password of a secret folder")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Password updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid new password"),
        @ApiResponse(responseCode = "401", description = "Current password is incorrect"),
        @ApiResponse(responseCode = "404", description = "Secret folder not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UpdateFolderSecretPasswordOutput> updatePassword(
            @PathVariable Long folderSecretId,
            @RequestBody UpdateFolderSecretPasswordRequest request) {
        try {
            UpdateFolderSecretPasswordCommand command = new UpdateFolderSecretPasswordCommand(
                folderSecretId,
                request.getCurrentPassword(),
                request.getNewPassword(),
                request.getUserId()
            );
            UpdateFolderSecretPasswordOutput output = folderSecretCommandProcessor.updatePassword(command);
            
            if (output.isSuccess()) {
                return ResponseEntity.ok(output);
            } else {
                return ResponseEntity.status(400).body(output);
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la modification du mot de passe : " + e.getMessage());
            return ResponseEntity.status(500).body(new UpdateFolderSecretPasswordOutput(false, "Erreur interne du serveur"));
        }
    }

    @DeleteMapping("/{folderSecretId}")
    @Operation(summary = "Delete secret folder", description = "Permanently delete a secret folder")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Folder deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Folder not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DeleteFolderSecretOutput> deleteFolderSecret(
            @PathVariable Long folderSecretId,
            @RequestParam Long userId) {
        try {
            DeleteFolderSecretCommand command = new DeleteFolderSecretCommand(folderSecretId, userId);
            DeleteFolderSecretOutput output = folderSecretCommandProcessor.deleteFolderSecret(command);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la suppression du dossier secret : " + e.getMessage());
            return ResponseEntity.status(500).body(new DeleteFolderSecretOutput(false, "Erreur interne du serveur"));
        }
    }
}