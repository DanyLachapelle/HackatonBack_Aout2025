package school.token.groupe7_hackatonback_aout2025.application.features.file.commands;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class FileCommandProcessor {
    private final ICommandHandler<CreateFileCommand, CreateFileOutput> createFileHandler;
    private final ICommandHandler<UploadFileCommand, UploadFileOutput> uploadFileHandler;


    public FileCommandProcessor(ICommandHandler<CreateFileCommand, CreateFileOutput> createFileHandler, ICommandHandler<UploadFileCommand, UploadFileOutput> uploadFileHandler) {
        this.createFileHandler = createFileHandler;
        this.uploadFileHandler = uploadFileHandler;
    }

    public CreateFileOutput createFile(CreateFileCommand command) {
        return createFileHandler.handle(command);
    }

    public UploadFileOutput uploadFile(UploadFileCommand command) {
        return uploadFileHandler.handle(command);
    }
}
