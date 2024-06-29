import React, { ReactNode, useMemo, useState } from "react";
import { createPortal } from "react-dom";
import { Toast, ToastTypes } from "../components/Toast";
import { ToastContext } from "./ToastContext";

interface ToastProps {
    message: string;
    type: ToastTypes;
}

export const ToastProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [toasts, setToasts] = useState<ToastProps[]>([]);

    const add = (message: string, type: ToastTypes) => {
        setToasts((old) => {
            if (old.length >= 3) {
                return old;
            }
            return [...old, { message, type }]
        });
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
                    <div className="flex justify-end mr-4 mt-1">
                        <Toast
                            key={index}
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
