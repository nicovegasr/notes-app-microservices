import { http } from "../api/http/axios"
import { useDataMutation } from "../api/http/hooks/useMutation"
import { User } from "../models/User"

const AuthRepository = () => {
    const baseUrl = "http://localhost:8082"

    const {
        mutate: loginMutate,
        status: loginStatus,
        data: loginData
    } = useDataMutation<User>({
        key: 'login',
        mutation: (user: User) => http.post<User, User>(baseUrl + "/api/v1/auth/login", user),
    })

    const {
        mutate: registerMutate,
        status: registerStatus,
        data: registerData
    } = useDataMutation<User>({
        key: 'register',
        mutation: (user: User) => http.post<User, User>(baseUrl + "/api/v1/auth/register", user),
    })

    const loginUser = async (user: User) => {
        return await loginMutate(user)
    };

    const registerUser = async (user: User) => {
        return await registerMutate(user)
    }

    return {
        login: loginUser,
        loginResponse: { status: loginStatus, data: loginData},
        register: registerUser,
        registerResponse: { status: registerStatus, data: registerData}
    }
}

export default AuthRepository;