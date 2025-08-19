package school.token.groupe7_hackatonback_aout2025.controller.file;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.token.groupe7_hackatonback_aout2025.application.dto.CreateFileRequest;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.FileCommandProcessor;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileOutput;


@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class FileCommandController {
    private final FileCommandProcessor fileCommandProcessor;

    public FileCommandController(FileCommandProcessor fileCommandProcessor) {
        this.fileCommandProcessor = fileCommandProcessor;
    }

    @PostMapping("/CreateFile")
    @ApiResponse(responseCode = "200", description = "Create a new file")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public CreateFileOutput createFile(@RequestBody CreateFileRequest request) {
        System.out.println(">>> REQUEST userId = " + request.getUserId()); // <--- debug

        CreateFileCommand command = new CreateFileCommand(
                request.getParentPath(),
                request.getName(),
                request.getContent(),
                request.getUserId()
        );
        System.out.println(">>> COMMAND userId = " + command.getUserId());

        System.out.println("REQUEST JSON = " + request);
        return fileCommandProcessor.createFile(command);
    }

    @PostMapping(
            value = "/UploadFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Upload a file"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UploadFileOutput uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestParam String parentPath,
            @RequestParam(defaultValue = "1") Long userId
    ) {
        UploadFileCommand command = new UploadFileCommand();
        command.setMultipartFile(file);
        command.setParentPath(parentPath);
        command.setUserId(userId);

        return fileCommandProcessor.uploadFile(command);
    }


}
