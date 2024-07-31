
import { useContext } from "react";
import { http } from "../api/http/axios";
import { useData } from "../api/http/hooks/useData";
import { useDataMutation } from "../api/http/hooks/useMutation";
import { envs } from "../environment/getEnvs";
import { AuthContext } from "../features/auth/context/AuthContext";
import { Folder } from "../models/Folder";
import { Note } from "../models/Note";

interface CreateFolder {
    name: string;
    user: string;
}


const NotesRepository = () => {
    const user = useContext(AuthContext)?.user;

    const baseUrl = envs.notes_url;

    const getFolders = useData<Folder[]>({
        key: 'folders',
        fetcher: () => http.get<Folder[]>(baseUrl + `/api/v1/folders?username=${user?.username}`),
        enabled: !!user
    })

    const getNotesByFolder = (folderId: string) => {
        return useData<Note[]>({
            key: 'notes',
            fetcher: () => http.get<Note[]>(baseUrl + `/api/v1/folders/${folderId}/notes?username=${user?.username}`),
            enabled: !!folderId && !!user
        })
    }

    const {
        mutate: createFolderMutation,
    } = useDataMutation<CreateFolder>({
        key: 'folders',
        mutation: (createFolder: CreateFolder) => http.post<CreateFolder, CreateFolder>(baseUrl + "/api/v1/folders", createFolder),
    })

    const createFolderQuery = async (createFolder: CreateFolder) => {
        return await createFolderMutation(createFolder);
    }

    const {
        mutate: createNoteMutation,
    } = useDataMutation<Note>({
        key: 'notes',
        mutation: (noteToCreate: Note) => http.post<Note, Note>(baseUrl + "/api/v1/notes", { ...noteToCreate, username: user?.username as string }),
    })

    const createNoteQuery = async (noteToCreate: Note) => {
        return await createNoteMutation(noteToCreate);
    }

    const {
        mutate: deleteNoteMutation,
    } = useDataMutation<Note>({
        key: 'notes',
        mutation: (notetoDelete: Note) => http.delete<Note>(baseUrl + "/api/v1/notes/" +
            notetoDelete.noteId +
            "?username=" + user?.username +
            "&folderId=" + notetoDelete.folderId),
    })

    const deleteNoteQuery = async (notetoDelete: Note) => {
        return await deleteNoteMutation(notetoDelete);
    }

    return {
        getFolders,
        getNotesByFolder,
        createFolderQuery,
        createNoteQuery,
        deleteNoteQuery
    }
}

export default NotesRepository;