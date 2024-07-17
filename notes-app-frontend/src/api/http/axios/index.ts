import axios, { AxiosRequestHeaders } from 'axios';

type Headers = AxiosRequestHeaders;

const get = async <T>(url: string, headers?: Headers): Promise<T> => {
    const response = await axios.get<T>(url, { headers });
    return response.data;
}

const post = async <T, K>(url: string, body: K, headers?: Headers): Promise<T> => {
    const response = await axios.post<T>(url, body, {
        headers: {
            ...headers,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    return response.data;
}

const axiosDelete = async <T>(url: string, headers?: Headers): Promise<T> => {
    const response = await axios.delete<T>(url, { headers });
    return response.data;
}


export const http = {
    get,
    post,
    delete: axiosDelete
}
