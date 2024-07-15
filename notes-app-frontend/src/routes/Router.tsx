import { Route, Routes } from "react-router-dom";
import Login from "../features/auth/pages/Login";
import { Register } from "../features/auth/pages/Register";
import { Home } from "../features/notes/pages/Home";


export const Router = () => {
    return (
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/" element={<Home />} />
        </Routes>
    );
}