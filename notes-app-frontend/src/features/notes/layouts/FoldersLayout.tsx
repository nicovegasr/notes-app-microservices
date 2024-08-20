import { Folder } from "../../../models/Folder";
import { FolderButton } from "../components/FolderButton";

interface FoldersLayoutProps {
    folders: Folder[];
    onFolderClick: (folder: Folder) => void;
}

export const FoldersLayout = ({ folders, onFolderClick }: FoldersLayoutProps) => {
    return (
        <div className="h-full w-full flex flex-col items-center justify-center">
            <h1 className="folders-title text-2xl font-bold text-center">
                Your folders:
            </ h1>
            <div className="flex flex-row">
                {folders.map((folder) => (
                    <div
                        key={folder.folderId}
                        className="folders-animation "
                    >
                        <FolderButton
                            folder={folder}
                            onFolderClick={onFolderClick}
                        />
                    </div>
                ))}
            </div>
        </div>
    );
}