import { Folder } from "@/src/models/Folder";
import { useEffect, useState } from "react";
import { SelectedFolder } from "../usecases/SelectedFolder";
import { FoldersLayout } from "./FoldersLayout";
import { NotesLayout } from "./NotesLayout";

interface UserLayoutProps {
    folders: Folder[];
    initialSelectedFolderId?: string;
}

export const UserLayout = ({ folders, initialSelectedFolderId }: UserLayoutProps) => {
    const [selectedFolder, setSelectedFolder] = useState<Folder | null>(null);

    useEffect(() => {
        if (initialSelectedFolderId) {
            const folder = folders.find(f => f.folderId === initialSelectedFolderId);
            if (folder) {
                setSelectedFolder(folder);
            }
        }
    }, [initialSelectedFolderId, folders]);

    const handleFolderClick = (folder: Folder) => {
        setSelectedFolder((old) => old !== folder ? folder : null);
    };

    return (
        <div className="h-full w-full" >
            {selectedFolder == null &&
                <FoldersLayout
                    folders={folders}
                    onFolderClick={handleFolderClick}
                />
            }
            {selectedFolder && (
                <div className="flex flex-col h-full">
                    <SelectedFolder
                        selectedFolder={selectedFolder}
                        handleFolderClick={handleFolderClick}
                    />
                    <div className="flex-1">
                        <NotesLayout
                            folder={selectedFolder}
                        />
                    </div>
                </div>
            )}
        </div >
    );
};