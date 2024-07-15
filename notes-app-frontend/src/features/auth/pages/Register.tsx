import {Image, Link} from "@nextui-org/react";
import {User} from "../../../models/User";
import AuthRepository from "../../../repositories/AuthRepository";
import {UserForm} from "../components/UserForm";
import {useToast} from "../../commons/hooks/useToasts";
import { useNavigate } from "react-router-dom";

export const Register = () => {

    const {register } = AuthRepository();

    const navigate = useNavigate();

    const toast = useToast();

    const registerUser = (user: User) => {
        register(user).then(() => {
            toast.add("User registered successfully", "success");
            navigate("/login");
        }).catch((error) => {
            const message = error.response?.data
            switch (typeof message) {
                case "string":
                    toast.add(message, "error");
                    break;
                default:
                    toast.add("An error occurred while registering the user", "error");
                    break;
            }
        })
    }

    return (
        <div className="h-full w-full flex flex-col items-center justify-center">
            <div
                className="bg-white p-8 w-4/5 md:w-1/2 lg:w-1/3 flex flex-col items-center justify-center space-y-6 shadow-lg rounded-lg animate-fadeIn">
                <div className="flex items-center justify-center p-4 w-full h-20 animate-fadeIn">
                    <Image src="icons/user.png" alt="Note icon" width={50} height={50}/>
                    <h1 className="text-5xl font-bold ml-4">Register</h1>
                </div>
                <UserForm mode="register" onSend={registerUser}/>
                <Link href="/login"> Already have an account? Login </Link>
            </div>
        </div>

    );
}