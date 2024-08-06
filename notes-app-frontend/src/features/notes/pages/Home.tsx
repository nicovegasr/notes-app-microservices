import { Folder } from "@/src/models/Folder";
import FolderRepository from "../../../repositories/FolderRepository";
import { LoadingUserNotes } from "../atomic/LoadingUserNotes";
import { UserLayout } from "../layouts/UserLayout";
import { UserWithoutFolders } from "../usecases/UserWithoutFolders";
import { useLocation } from "react-router-dom";

export const Home = () => {
    const { getFolders } = FolderRepository();

    const { state } = useLocation();
    const selectedFolderId = state?.selectedFolderId;

    const folders: Folder[] | undefined = getFolders.data;

    return (
        <>
            {folders && folders.length > 0 && (
                <UserLayout
                    folders={folders}
                    initialSelectedFolderId={selectedFolderId}
                />
            )}
            {folders && folders.length === 0 && (
                <UserWithoutFolders />
            )}
            {getFolders.status === "pending" && (
                <LoadingUserNotes />
            )}
        </>
    );
}