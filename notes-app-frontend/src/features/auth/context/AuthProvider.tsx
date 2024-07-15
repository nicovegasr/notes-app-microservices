import { User } from "@/src/models/User";
import React, { ReactNode, useEffect, useMemo, useState } from "react";
import { AuthContext } from "./AuthContext";
import { useNavigate, useLocation } from "react-router-dom";

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User>();

    const navigate = useNavigate();
    
    const location = useLocation();
    
    const routerWithoutAuth = ["/login", "/register"];

    useEffect(() => {
        if (!user && !routerWithoutAuth.includes(location.pathname)) {
            navigate("/login");
        }
    }, [user, navigate]);
    
    const login = (user: User) => {
        setUser(user);
    }

    const logout = () => {
        setUser(undefined);
    }

    const contextValue = useMemo(() => ({
        login, logout, user
    }), [user]);

    return (
        <AuthContext.Provider value={contextValue}>
            {children}
        </AuthContext.Provider>
    );
};
