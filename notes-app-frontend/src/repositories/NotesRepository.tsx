
import { useContext } from "react";
import { http } from "../api/http/axios";
import { useData } from "../api/http/hooks/useData";
import { useDataMutation } from "../api/http/hooks/useMutation";
import { envs } from "../environment/getEnvs";
import { AuthContext } from "../features/auth/context/AuthContext";
import { Note } from "../models/Note";

interface NotesRepositoryParams {
    folderId?: string
    noteId?: string
}

const NotesRepository = ({ folderId, noteId }: NotesRepositoryParams) => {
    const user = useContext(AuthContext)?.user;

    const baseUrl = envs.notes_url;

    const getNoteDetailsQuery = useData<Note>({
        key: 'note-details',
        fetcher: () => http.get<Note>(baseUrl + `/api/v1/notes/${noteId}`),
        enabled: !!noteId && !!user
    })


    const getNotesByFolder = useData<Note[]>({
        key: 'notes',
        fetcher: () => http.get<Note[]>(baseUrl + `/api/v1/folders/${folderId}/notes`),
        enabled: !!folderId
    })

    const { mutate: createNoteMutation } = useDataMutation<Note>({
        key: 'notes',
        mutation: (noteToCreate: Note) => http.post<Note, Note>(baseUrl + "/api/v1/notes", { ...noteToCreate, username: user?.username as string }),
        onMutationSucess: () => getNotesByFolder.refetch()
    })

    const createNoteQuery = async (noteToCreate: Note) => {
        return await createNoteMutation(noteToCreate);
    }

    const { mutate: updateNoteMutation } = useDataMutation<Note>({
        key: 'note-details',
        mutation: (noteToUpdate: Note) => http.put<Note, Note>(baseUrl + "/api/v1/notes/" + noteToUpdate.noteId, noteToUpdate),
        onMutationSucess: () => getNoteDetailsQuery.refetch()
    })

    const updateNoteQuery = async (noteToUpdate: Note) => {
        return await updateNoteMutation(noteToUpdate);
    }

    const { mutate: deleteNoteMutation } = useDataMutation<Note>({
        key: 'notes',
        mutation: (notetoDelete: Note) => http.delete<Note>(baseUrl + "/api/v1/notes/" +
            notetoDelete.noteId +
            "?username=" + user?.username +
            "&folderId=" + notetoDelete.folderId),
        onMutationSucess: () => getNotesByFolder.refetch()
    })

    const deleteNoteQuery = async (notetoDelete: Note) => {
        return await deleteNoteMutation(notetoDelete);
    }

    return {
        getNoteDetailsQuery,
        getNotesByFolder,
        createNoteQuery,
        updateNoteQuery,
        deleteNoteQuery
    }
}

export default NotesRepository;