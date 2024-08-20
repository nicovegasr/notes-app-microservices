import { Note } from "@/src/models/Note";
import { TrashIcon } from "@heroicons/react/24/outline";

interface NoteButtonProps {
    note: Note;
    onClick: (note: Note) => void;
    onDelete: (note: Note) => void;
}

export const NoteCard = ({ note, onClick, onDelete }: NoteButtonProps) => {
    return (
        <div className="bg-gray-100 rounded-lg shadow-md flex flex-col h-40">
            <button className="self-end mt-4 mr-4 absolute" onClick={() => onDelete(note)}>
                <TrashIcon className="h-5 w-5 fill-red-200" />
            </button>
            <button onClick={() => onClick(note)} className="h-full self-center w-full">
                <p className="text-sm sm:text-medium text-center truncate">{note.title}</p>
            </button>
        </div >
    );
};