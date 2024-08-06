import { useMutation, useQueryClient } from "@tanstack/react-query"

interface UseDataMutation<T> {
    key: string,
    mutation: (data: T) => Promise<T>
    onMutationSucess?: () => void
}

export const useDataMutation = <T>({ key, mutation, onMutationSucess }: UseDataMutation<T>) => {
    const { mutateAsync: reactQueryMutate, status, data} = useMutation({ 
        mutationFn: (data: T) => mutation(data),
        onSuccess: () => {
            if (onMutationSucess) onMutationSucess()
        }
    })
    const queryClient = useQueryClient()

    const mutate = async (data: T) => {
        await reactQueryMutate(data)
        await queryClient.invalidateQueries({ queryKey: [key] })
        
    }

    return { mutate, status, data }
}