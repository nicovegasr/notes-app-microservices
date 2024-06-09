import React, { ReactNode, useMemo, useState } from "react";
import { Toast, ToastTypes } from "../components/Toast";
import { ToastContext } from "./ToastContext";
import { createPortal } from "react-dom";

interface ToastProps {
    message: string;
    type: ToastTypes;
}

export const ToastProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [toasts, setToasts] = useState<ToastProps[]>([]);

    const add = (message: string, type: ToastTypes) => {
        setToasts((old) => [...old, { message, type }]);
    };

    const remove = (index: number) => {
        setToasts(toasts.filter((_, i) => i !== index));
    };

    const contextValue = useMemo(() => ({ add }), []);

    return (
        <ToastContext.Provider value={contextValue}>
            {children}
            {createPortal(
                toasts.map((toast, index) => (
                    <div className="flex justify-end mr-4">
                        <Toast
                            message={toast.message}
                            type={toast.type}
                            onClose={() => remove(index)} />
                    </div>
                )),
                document.body
            )}
        </ToastContext.Provider>
    );
};
