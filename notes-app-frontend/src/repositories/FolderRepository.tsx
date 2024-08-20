
import { useContext } from "react";
import { http } from "../api/http/axios";
import { useData } from "../api/http/hooks/useData";
import { useDataMutation } from "../api/http/hooks/useMutation";
import { envs } from "../environment/getEnvs";
import { AuthContext } from "../features/auth/context/AuthContext";
import { Folder } from "../models/Folder";

interface CreateFolder {
    name: string;
    user: string;
}


const FolderRepository = () => {
    const user = useContext(AuthContext)?.user;

    const baseUrl = envs.notes_url;

    const getFolders = useData<Folder[]>({
        key: 'folders',
        fetcher: () => http.get<Folder[]>(baseUrl + `/api/v1/folders?username=${user?.username}`),
        enabled: !!user
    })


    const {
        mutate: createFolderMutation,
    } = useDataMutation<CreateFolder>({
        key: 'folders',
        mutation: (createFolder: CreateFolder) => http.post<CreateFolder, CreateFolder>(baseUrl + "/api/v1/folders", createFolder),
    })

    const createFolderQuery = async (createFolder: CreateFolder) => {
        return await createFolderMutation(createFolder);
    }

    return {
        getFolders,
        createFolderQuery
    }
}

export default FolderRepository;