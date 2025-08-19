package school.token.groupe7_hackatonback_aout2025.controller.file;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.FileQueryProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.queries.findFoldersByUserAndPath.FindFoldersByUserAndPathQuery;
import school.token.groupe7_hackatonback_aout2025.domain.File;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class FileQueryController {

    private final FileQueryProcessor fileQueryProcessor;

    public FileQueryController(FileQueryProcessor fileQueryProcessor) {
        this.fileQueryProcessor = fileQueryProcessor;
    }



    @GetMapping("/getFileByPath")
    @ApiResponse(responseCode = "200", description = "Retrieve file by path")
    @ApiResponse(responseCode = "404", description = "File not found for the given path")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<FileDto>> getFileByPath(@RequestParam("path") String path,
                                                       @RequestParam("userId") Long userId)
    {
        try {
            GetFileByPathOutput output = fileQueryProcessor.getFileByPath(new GetFileByPathQuery(path, userId));
            return ResponseEntity.ok(output.getFiles());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des fichiers : " + e.getMessage());
            return ResponseEntity.status(500).body(null); // Retourne une réponse 500 en cas d'erreur
        }
    }
    @GetMapping("/getContentByFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve file content by path"),
            @ApiResponse(responseCode = "404", description = "File not found for the given path"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> getContentByFile(@RequestParam("path") String path,
                                                   @RequestParam("userId") Long userId) {
        try {
            GetContentByFileOutput output = fileQueryProcessor.getContentByFile(
                    new GetContentByFileQuery(path, userId)
            );

            if (output.getContent() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(output.getContent());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération du contenu du fichier : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

}
