import { useMutation, useQueryClient } from "@tanstack/react-query"

interface UseDataMutation<T> {
    key: string,
    mutation: (data: T) => Promise<T>
}

export const useDataMutation = <T>({ key, mutation }: UseDataMutation<T>) => {
    const { mutateAsync: reactQueryMutate, status, data} = useMutation({ mutationFn: (data: T) => mutation(data) })
    const queryClient = useQueryClient()

    const mutate = async (data: T) => {
        await Promise.all([
            reactQueryMutate(data),
            queryClient.invalidateQueries({ queryKey: [key] })
        ])
    }

    return { mutate, status, data }
}