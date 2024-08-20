import { CircularProgress } from "@nextui-org/react";

export const LoadingUserNotes = () =>
    <div className="h-full w-full flex flex-col items-center justify-center">
        <CircularProgress
            aria-label="Loading..."
            classNames={{
                svg: "w-36 h-36 drop-shadow-md",
            }}

        />
        <h1 className="text-2xl font-bold text-center mt-4">Loading...</h1>
    </div>