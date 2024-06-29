
import { useContext } from "react";
import { http } from "../api/http/axios";
import { useData } from "../api/http/hooks/useData";
import { envs } from "../environment/getEnvs";
import { AuthContext } from "../features/auth/context/AuthContext";
import { Layout } from "../models/Layout";

const NotesRepository = () => {
    const user = useContext(AuthContext)?.user;
    
    const baseUrl = envs.notes_url;

    const getNotes = useData<Layout>({
        key: 'notes',
        fetcher: () => http.get<Layout>(baseUrl + `/api/v1/layout?username=${user?.username}&email=${user?.email}`),
        enabled: !! user
    })

    return {
        getNotesQuery: getNotes
    }
}

export default NotesRepository;