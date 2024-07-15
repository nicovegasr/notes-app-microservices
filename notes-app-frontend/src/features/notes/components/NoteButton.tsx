import { Note } from "@/src/models/Note";

interface NoteButtonProps {
    note: Note;
    onNoteClick: (note: Note) => void;
}

export const NoteButton = ({ note, onNoteClick }: NoteButtonProps) =>
    <button onClick={() => onNoteClick(note)}>
        <div key={note.id} className="bg-gray-100 rounded-lg shadow-md p-4 flex items-center justify-center h-40">
            <p className="text-sm sm:text-medium text-center truncate">{note.name}</p>
        </div>
    </button>