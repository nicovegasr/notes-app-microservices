import { useContext } from "react";
import { ToastContext } from "../contexts/ToastContext";
import { ToastTypes } from "../components/Toast";

interface ToastProps {
    add: (message: string, type: ToastTypes) => void;
}

export const useToast = (): ToastProps => {
    const context = useContext(ToastContext);
    if (!context) {
        throw new Error("useToast must be used within a ToastProvider");
    }
    return context;
};