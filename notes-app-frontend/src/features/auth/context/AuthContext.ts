import { User } from "@/src/models/User";
import { createContext } from "react";

interface AuthContextType {
    user?: User;
    login: (user: User) => void;
    logout: () => void;
}

export const AuthContext = createContext<AuthContextType | undefined>(undefined);
