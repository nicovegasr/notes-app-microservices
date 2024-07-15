import { Folder } from "@/src/models/Folder";
import { FolderButton } from "../components/FolderButton";

interface SelectedFolderProps {
    selectedFolder: Folder;
    handleFolderClick: (folder: Folder) => void
}


export const SelectedFolder = ({ selectedFolder, handleFolderClick }: SelectedFolderProps) =>
    <div className="flex flex-row mt-16 ml-3 ">
        <div
            key={selectedFolder.folderId}
            className="folders-animation"
        >
            <FolderButton
                folder={selectedFolder}
                onFolderClick={handleFolderClick}
            />
        </div>
    </div>