package school.token.groupe7_hackatonback_aout2025.application.features.file.commands;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile.DeleteFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile.DeleteFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent.UpdateFileContentCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent.UpdateFileContentOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class FileCommandProcessor {
    private final ICommandHandler<CreateFileCommand, CreateFileOutput> createFileHandler;
    private final ICommandHandler<UploadFileCommand, UploadFileOutput> uploadFileHandler;
    private final ICommandHandler<UpdateFileContentCommand, UpdateFileContentOutput> updateFileContentHandler;
    private final ICommandHandler<DeleteFileCommand, DeleteFileOutput> deleteFileHandler;

    public FileCommandProcessor(ICommandHandler<CreateFileCommand, CreateFileOutput> createFileHandler, ICommandHandler<UploadFileCommand, UploadFileOutput> uploadFileHandler, ICommandHandler<UpdateFileContentCommand, UpdateFileContentOutput> updateFileContentHandler, ICommandHandler<DeleteFileCommand, DeleteFileOutput> deleteFileHandler) {
        this.createFileHandler = createFileHandler;
        this.uploadFileHandler = uploadFileHandler;
        this.updateFileContentHandler = updateFileContentHandler;
        this.deleteFileHandler = deleteFileHandler;
    }

    public CreateFileOutput createFile(CreateFileCommand command) {
        return createFileHandler.handle(command);
    }

    public UploadFileOutput uploadFile(UploadFileCommand command) {
        return uploadFileHandler.handle(command);
    }

    public UpdateFileContentOutput updateFileContent(UpdateFileContentCommand command) {
        return updateFileContentHandler.handle(command);
    }

    public DeleteFileOutput deleteFile(DeleteFileCommand command) {
        return deleteFileHandler.handle(command);
    }
}
