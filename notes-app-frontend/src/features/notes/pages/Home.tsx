import { Folder } from "@/src/models/Folder";
import FolderRepository from "../../../repositories/FolderRepository";
import { LoadingUserNotes } from "../atomic/LoadingUserNotes";
import { UserLayout } from "../layouts/UserLayout";
import { UserWithoutFolders } from "../usecases/UserWithoutFolders";

export const Home = () => {
    const { getFolders } = FolderRepository();

    const folders: Folder[] | undefined = getFolders.data;

    return (
        <>
            {folders && folders.length > 0 && (
                <UserLayout
                    folders={folders}
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