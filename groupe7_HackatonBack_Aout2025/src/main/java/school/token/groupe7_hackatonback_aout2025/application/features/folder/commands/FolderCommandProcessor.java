package school.token.groupe7_hackatonback_aout2025.application.features.folder.commands;

import org.springframework.stereotype.Service;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder.SetFavoriteFolderCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.SetFavoriteFolder.SetFavoriteFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder.CreateFolderCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.createFolder.CreateFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.renameFolder.RenameFolderCommand;
import school.token.groupe7_hackatonback_aout2025.application.features.folder.commands.renameFolder.RenameFolderOutput;
import school.token.groupe7_hackatonback_aout2025.application.utils.ICommandHandler;

@Service
public class FolderCommandProcessor {
    private final ICommandHandler<CreateFolderCommand, CreateFolderOutput> _createFolderHandler;
    private final ICommandHandler<SetFavoriteFolderCommand, SetFavoriteFolderOutput> _setFavoriteFolderHandler;
    private final ICommandHandler<RenameFolderCommand, RenameFolderOutput> _renameFolderHandler;
    public FolderCommandProcessor(ICommandHandler<CreateFolderCommand, CreateFolderOutput> createFolderHandler, ICommandHandler<SetFavoriteFolderCommand, SetFavoriteFolderOutput> setFavoriteFolderHandler, ICommandHandler<RenameFolderCommand, RenameFolderOutput> renameFolderHandler) {
        _createFolderHandler = createFolderHandler;
        _setFavoriteFolderHandler = setFavoriteFolderHandler;
        _renameFolderHandler = renameFolderHandler;
    }

    public CreateFolderOutput createFolder(CreateFolderCommand command) {
        return _createFolderHandler.handle(command);
    }

    public SetFavoriteFolderOutput setFavoriteFolder(SetFavoriteFolderCommand command) {
        return _setFavoriteFolderHandler.handle(command);
    }

    public RenameFolderOutput renameFolder(RenameFolderCommand command) {
        return _renameFolderHandler.handle(command);
    }
}
