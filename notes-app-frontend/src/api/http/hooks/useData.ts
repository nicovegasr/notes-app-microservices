import { useQuery } from "@tanstack/react-query"

type Status = 'pending' | 'error' | 'success'

interface UseData<T> {
    key: string,
    fetcher: () => Promise<T>
}

interface Response<T> {
    data: T | undefined
    status: Status
}

export const useData = <T>({ key, fetcher }: UseData<T>): Response<T> => {
    const { data, status } = useQuery<T, string>({ queryKey: [key], queryFn: fetcher})
    return { data, status }
}