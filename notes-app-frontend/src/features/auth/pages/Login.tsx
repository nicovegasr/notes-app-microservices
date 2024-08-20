import { Image, Link } from "@nextui-org/react";
import { useNavigate } from "react-router-dom";
import { User } from "../../../models/User";
import AuthRepository from "../../../repositories/AuthRepository";
import { useToast } from "../../commons/hooks/useToasts";
import { UserForm } from "../components/UserForm";
import { useEffect } from "react";
import { useAuth } from "../hooks/useAuth";

const Login = () => {

    const { login, loginResponse } = AuthRepository();

    const toast = useToast();
    const auth = useAuth();

    const navigate = useNavigate();

    useEffect(() => {
        if (loginResponse.status === "success") {
            auth.login(loginResponse.data as User);
            navigate("/");
        }
    }, [loginResponse.status]);

    const loginUser = (user: User) => {
        login(user).then(() => {
            toast.add("User logged in successfully", "success");
        }).catch(() => {
            toast.add("Unauthorized", "error");
        })
    };

    return (
        <div className="h-full w-full flex flex-col items-center justify-center">
            <div className="bg-white p-8 w-4/5 md:w-1/2 lg:w-1/3 flex flex-col items-center justify-center space-y-6 shadow-lg rounded-lg animate-fadeIn">
                <div className="flex items-center justify-center p-4 w-full h-20 animate-fadeIn">
                    <Image src="icons/user.png" alt="Note icon" width={50} height={50} />
                    <h1 className="text-5xl font-bold ml-4">Login</h1>
                </div>
                <UserForm
                    mode="login"
                    onSend={loginUser}
                />
                <Link href="/register" > Don't have an account? Sign in </Link>
            </div>

        </div>
    );
};

export default Login;
