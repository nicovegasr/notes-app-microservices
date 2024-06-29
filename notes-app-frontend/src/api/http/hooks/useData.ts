import { useQuery } from "@tanstack/react-query"

type Status = 'pending' | 'error' | 'success'

interface UseData<T> {
    key: string,
    fetcher: () => Promise<T>,
    enabled?: boolean
}

interface Response<T> {
    data: T | undefined
    status: Status
}

export const useData = <T>({ key, fetcher, enabled }: UseData<T>): Response<T> => {
    const { data, status } = useQuery<T, string>(
        {
            queryKey: [key],
            queryFn: fetcher,
            enabled: enabled ?? true,
            refetchOnWindowFocus: false,
        },)
    return { data, status }
}