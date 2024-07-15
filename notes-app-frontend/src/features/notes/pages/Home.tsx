import { Layout } from "@/src/models/Layout";
import NotesRepository from "../../../repositories/NotesRepository";
import { LoadingUserNotes } from "../atomic/LoadingUserNotes";
import { UserLayout } from "../layouts/UserLayout";
import { UserWithoutFolders } from "../usecases/UserWithoutFolders";

export const Home = () => {
    const { getLayout } = NotesRepository();

    return (
        <>
            {getLayout.data && (getLayout.data as Layout).folders.length > 0 && (
                <UserLayout
                    layout={getLayout.data as Layout}
                />
            )}
            {getLayout.data && (getLayout.data as Layout).folders.length === 0 && (
                <UserWithoutFolders />
            )}
            {getLayout.status === "pending" && (
                <LoadingUserNotes />
            )}
        </>
    );
}