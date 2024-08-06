import { User } from "@/src/models/User";
import React, { ReactNode, useEffect, useMemo, useState } from "react";
import { AuthContext } from "./AuthContext";
import { useNavigate, useLocation } from "react-router-dom";

const USER_STORAGE_KEY = 'user_session';

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | undefined>(() => {
        const storedUser = localStorage.getItem(USER_STORAGE_KEY);
        return storedUser ? JSON.parse(storedUser) : undefined;
    });

    const navigate = useNavigate();
    
    const location = useLocation();
    
    const routerWithoutAuth = ["/login", "/register"];

    useEffect(() => {
        if (!user && !routerWithoutAuth.includes(location.pathname)) {
            navigate("/login");
        }
    }, [user, navigate, location.pathname]);
    
    const login = (user: User) => {
        setUser(user);
        localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user));
    }

    const logout = () => {
        setUser(undefined);
        localStorage.removeItem(USER_STORAGE_KEY);
        navigate("/login");
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