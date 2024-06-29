import { CircularProgress } from "@nextui-org/react";

export const LoadingUserNotes = () =>
    <>
        <CircularProgress
            classNames={{
                svg: "w-36 h-36 drop-shadow-md",
            }}
        />
        <h1 className="text-2xl font-bold text-center mt-4">Loading user notes</h1>
    </>