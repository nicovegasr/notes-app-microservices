import { Layout } from "@/src/models/Layout";
import NotesRepository from "../../../repositories/NotesRepository";
import { LoadingUserNotes } from "../atomic/LoadingUserNotes";
import { UserWithoutFolders } from "../components/UserWithoutFolders";

export const Home = () => {

    const { getNotesQuery } = NotesRepository();

    console.log(getNotesQuery);

    return (
        <div className="absolute h-4/6 w-full flex flex-col items-center justify-center">

            {getNotesQuery.data && (getNotesQuery.data as Layout).folders.length === 0 &&
                <UserWithoutFolders />
            }
            {getNotesQuery.status == "pending" &&
                <LoadingUserNotes />
            }

        </div>
    );
}