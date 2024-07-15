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
            return [...old, { message, type }];
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
                <div className="toast fixed bottom-0 right-0 m-4 flex flex-col-reverse space-y-1 space-y-reverse">
                    {toasts.map((toast, index) => (
                        <Toast
                            key={index}
                            index={index}
                            message={toast.message}
                            type={toast.type}
                            onClose={remove}
                        />
                    ))}
                </div>,
                document.body
            )}
        </ToastContext.Provider>
    );
};