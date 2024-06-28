import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "../features/auth/pages/Login";
import { Register } from "../features/auth/pages/Register";
import { AuthProvider } from "../features/auth/context/AuthProvider";


export const Router = () => {
    return (
        <BrowserRouter>
            <AuthProvider>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                </Routes>
            </AuthProvider>
        </BrowserRouter>
    );
}