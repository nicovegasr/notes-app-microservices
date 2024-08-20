import { Image } from "@nextui-org/react";
import { Folder } from "../../../models/Folder";

interface FolderProps {
    folder: Folder;
    onFolderClick: (folder: Folder) => void;
}

export const FolderButton = ({ folder, onFolderClick }: FolderProps) =>
    <>
        <button onClick={() => onFolderClick(folder)}>
            <Image
                className="!mb-0"
                src="icons/folder.png"
                alt="Folder icon"
                width={100}
                height={100}
            />
        </button>
        <h2 className="max-w-[100px] text-l font-bold text-center -mt-4 truncate">
            {folder.name}
        </h2>
    </>