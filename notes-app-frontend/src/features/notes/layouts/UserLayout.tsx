import { Folder } from "@/src/models/Folder";
import { useState } from "react";
import { Layout } from "../../../models/Layout";
import { SelectedFolder } from "../usecases/SelectedFolder";
import { FoldersLayout } from "./FoldersLayout";
import { NotesLayout } from "./NotesLayout";

interface UserLayoutProps {
    layout: Layout;
}

export const UserLayout = ({ layout }: UserLayoutProps) => {
    const [selectedFolder, setSelectedFolder] = useState<Folder | null>(null);

    const handleFolderClick = (folder: Folder) => {
        setSelectedFolder((old) => old !== folder ? folder : null);
    };

    return (
        <div className="h-full w-full" >
            {selectedFolder == null &&
                <FoldersLayout
                    folders={layout.folders}
                    onFolderClick={handleFolderClick}
                />
            }
            {
                selectedFolder && (
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
                )
            }
        </div >
    );
};