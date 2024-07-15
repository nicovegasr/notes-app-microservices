
import { useContext } from "react";
import { http } from "../api/http/axios";
import { useData } from "../api/http/hooks/useData";
import { envs } from "../environment/getEnvs";
import { AuthContext } from "../features/auth/context/AuthContext";
import { Layout } from "../models/Layout";
import { useDataMutation } from "../api/http/hooks/useMutation";
import { Note } from "../models/Note";

interface CreateFolder {
    name: string;
    user: string;
}


const NotesRepository = () => {
    const user = useContext(AuthContext)?.user;

    const baseUrl = envs.notes_url;

    const getLayout = useData<Layout>({
        key: 'layout',
        fetcher: () => http.get<Layout>(baseUrl + `/api/v1/layouts?username=${user?.username}&email=${user?.email}`),
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
        key: 'layout',
        mutation: (createFolder: CreateFolder) => http.post<CreateFolder, CreateFolder>(baseUrl + "/api/v1/folders", createFolder),
    })

    const createFolderQuery = async (createFolder: CreateFolder) => {
        return await createFolderMutation(createFolder);
    }

    return {
        getLayout,
        getNotesByFolder,
        createFolderQuery
    }
}

export default NotesRepository;