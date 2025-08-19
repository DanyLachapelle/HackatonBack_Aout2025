package school.token.groupe7_hackatonback_aout2025.application.features.file.commands;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.createFile.CreateFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile.DeleteFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile.DeleteFileHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFile.DeleteFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFileById.DeleteFileByIdCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFileById.DeleteFileByIdHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFileById.DeleteFileByIdOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFolderById.DeleteFolderByIdCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.deleteFolderById.DeleteFolderByIdOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent.UpdateFileContentCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent.UpdateFileContentHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.updateFileContent.UpdateFileContentOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileHandler;
import school.token.groupe7_hackatonback_aout2025.application.features.file.commands.uploadFile.UploadFileOutput;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class FileCommandProcessor {
    private final ICommandHandler<CreateFileCommand, CreateFileOutput> createFileHandler;
    private final ICommandHandler<UploadFileCommand, UploadFileOutput> uploadFileHandler;
    private final ICommandHandler<UpdateFileContentCommand, UpdateFileContentOutput> updateFileContentHandler;
    private final ICommandHandler<DeleteFileCommand, DeleteFileOutput> deleteFileHandler;
    private final ICommandHandler<DeleteFileByIdCommand,DeleteFileByIdOutput> deleteFileByIdHandler;
    private final ICommandHandler<DeleteFolderByIdCommand, DeleteFolderByIdOutput> deleteFolderByIdHandler;

    public FileCommandProcessor(
            CreateFileHandler createFileHandler,
            UploadFileHandler uploadFileHandler,
            UpdateFileContentHandler updateFileContentHandler,
            DeleteFileHandler deleteFileHandler,
            DeleteFileByIdHandler deleteFileByIdHandler, ICommandHandler<DeleteFolderByIdCommand, DeleteFolderByIdOutput> deleteFolderByIdHandler) {
        this.createFileHandler = createFileHandler;
        this.uploadFileHandler = uploadFileHandler;
        this.updateFileContentHandler = updateFileContentHandler;
        this.deleteFileHandler = deleteFileHandler;
        this.deleteFileByIdHandler = deleteFileByIdHandler;
        this.deleteFolderByIdHandler = deleteFolderByIdHandler;
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

    public DeleteFileByIdOutput deleteFileById(DeleteFileByIdCommand command) {
        return deleteFileByIdHandler.handle(command);
    }

    public DeleteFolderByIdOutput deleteFolderById(DeleteFolderByIdCommand command) {
        return deleteFolderByIdHandler.handle(command);
    }
}
