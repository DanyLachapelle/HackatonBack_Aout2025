package school.token.groupe7_hackatonback_aout2025.controller.file;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.token.groupe7_hackatonback_aout2025.application.dto.FileDto;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.FileQueryProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.SearchFile.SearchFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.countFileByPath.CountFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.dowloadFile.DowloadFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.dowloadFile.DowloadFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getAudioFile.GetAudioFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile.GetContentByFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile.GetFavoriteFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFavoriteFile.GetFavoriteFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getFileByPath.GetFileByPathQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getImageFile.GetImageFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.getTextFile.GetTextFileQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.searchFileByType.SearchFileByTypeOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.searchFileByType.SearchFileByTypeQuery;
import school.token.groupe7_hackatonback_aout2025.application.features.file.query.countFileByPath.CountFileByPathOutput;

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

    @GetMapping("/getFavoriteFiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve favorite files for the user"),
            @ApiResponse(responseCode = "404", description = "No favorite files found for the user"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FileDto>> getFavoriteFiles(@RequestParam("userId") Long userId) {
        try {
            GetFavoriteFileOutput output = fileQueryProcessor.getFavoriteFile(new GetFavoriteFileQuery(userId));
            if (output.getFiles().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(output.getFiles());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des fichiers favoris : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/SearchFiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search files by query"),
            @ApiResponse(responseCode = "404", description = "No files found for the given query"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FileDto>> searchFiles(@RequestParam("query") String query,
                                                     @RequestParam("userId") Long userId) {
        try {
            SearchFileQuery searchFileQuery = new SearchFileQuery(query, userId);
            SearchFileOutput output = fileQueryProcessor.searchFile(searchFileQuery);

            if (output.getFiles().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(output.getFiles());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la recherche de fichiers : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/SearchFilesByType")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search files by type"),
            @ApiResponse(responseCode = "404", description = "No files found for the given type"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FileDto>> searchFilesByType(@RequestParam("contentType") String contentType,
                                                            @RequestParam("userId") Long userId) {
        try {
            SearchFileByTypeQuery searchFileByType = new SearchFileByTypeQuery(contentType, userId);
            SearchFileByTypeOutput output = fileQueryProcessor.searchFileByType(searchFileByType);

            if (output.getFiles().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(output.getFiles());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la recherche de fichiers par type : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/GetImageFiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve image files for the user"),
            @ApiResponse(responseCode = "404", description = "No image files found for the user"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FileDto>> getImageFiles(@RequestParam("userId") Long userId) {
        try {
            GetImageFileQuery getImageFileQuery = new GetImageFileQuery(userId);
            List<FileDto> images = fileQueryProcessor.getImageFile(getImageFileQuery).getFiles();
            if (images.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des fichiers image : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/GetAudioFiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve audio files for the user"),
            @ApiResponse(responseCode = "404", description = "No audio files found for the user"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FileDto>> getAudioFiles(@RequestParam("userId") Long userId) {
        try {
            GetAudioFileQuery getAudioFileQuery = new GetAudioFileQuery(userId);
            List<FileDto> audioFiles = fileQueryProcessor.getAudioFile(getAudioFileQuery).getAudioFiles();
            if (audioFiles.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(audioFiles);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des fichiers audio : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/GetTextFiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve text files for the user"),
            @ApiResponse(responseCode = "404", description = "No text files found for the user"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FileDto>> getTextFiles(@RequestParam("userId") Long userId) {
        try {
            GetTextFileQuery getTextFileQuery = new GetTextFileQuery(userId);
            List<FileDto> textFiles = fileQueryProcessor.getTextFile(getTextFileQuery).getTextFiles();
            if (textFiles.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(textFiles);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la récupération des fichiers texte : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/CountFilesByPath")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Count files by path"),
            @ApiResponse(responseCode = "404", description = "No files found for the given path"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Long> countFilesByPath(@RequestParam("path") String path,
                                                 @RequestParam("userId") Long userId) {
        try {
            CountFileByPathQuery countFileByPathQuery = new CountFileByPathQuery(path, userId);
            CountFileByPathOutput output = fileQueryProcessor.countFileByPath(countFileByPathQuery);
            return ResponseEntity.ok(output.getCount());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du comptage des fichiers par chemin : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("DownloadFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Download file by path"),
            @ApiResponse(responseCode = "404", description = "File not found for the given path"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> downloadFile(@RequestParam("path") String path,
                                          @RequestParam("userId") Long userId,
                                          @RequestParam(value = "inline", defaultValue = "false") boolean inline) {
        try {
            DowloadFileQuery downloadFileQuery = new DowloadFileQuery(path, userId, inline);
            DowloadFileOutput output = fileQueryProcessor.downloadFile(downloadFileQuery);

            if (output.getResource() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header("Content-Disposition", (inline ? "inline" : "attachment") + "; filename=\"" + output.getFilename() + "\"")
                    .contentType(org.springframework.http.MediaType.parseMediaType(output.getContentType()))
                    .body(output.getResource());
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du téléchargement du fichier : " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
