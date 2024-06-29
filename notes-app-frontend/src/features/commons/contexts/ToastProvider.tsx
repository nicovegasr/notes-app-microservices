import React, { ReactNode, useEffect, useMemo, useState } from "react";
import { Toast, ToastTypes } from "../components/Toast";
import { ToastContext } from "./ToastContext";
import { createPortal } from "react-dom";

interface ToastProps {
    message: string;
    type: ToastTypes;
}

export const ToastProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [toasts, setToasts] = useState<ToastProps[]>([]);

    useEffect(() => {
        if (toasts.length >= 3) {
            setToasts((old) => old.slice(0, 3));
        }
    }, [toasts.length])

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
                    <div className="bg-black flex justify-end mr-4 mt-4">
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
