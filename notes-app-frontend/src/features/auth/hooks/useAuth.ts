import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { User } from "@/src/models/User";

interface AuthProps {
    login: (user: User) => void;
    logout: () => void;
}

export const useAuth = (): AuthProps => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error("useAuth must be used within an AuthProvider");
    }
    return context;
};