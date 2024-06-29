import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "../features/auth/pages/Login";
import { Register } from "../features/auth/pages/Register";
import { AuthProvider } from "../features/auth/context/AuthProvider";
import { Home } from "../features/notes/pages/Home";


export const Router = () => {
    return (
        <BrowserRouter>
            <AuthProvider>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/" element={<Home />} />
                </Routes>
            </AuthProvider>
        </BrowserRouter>
    );
}