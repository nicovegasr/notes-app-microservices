import { useLocation, useNavigate } from "react-router-dom";
import { Note } from "../../../models/Note";
import NotesRepository from "../../../repositories/NotesRepository";
import { useToast } from "../../commons/hooks/useToasts";
import { NoteForm } from "../components/NoteForm";

export const NotePage = () => {
    const { state } = useLocation();
    const { createNoteQuery } = NotesRepository();

    const folderId = state?.folderId;
    const note = state?.note;

    const navigate = useNavigate();
    const toast = useToast();

    const handleCreateNote = (note: Note) => {
        createNoteQuery(note).then(() => {
            toast.add("Note created successfully", "success");
            navigate("/");
        }).catch((error) => {
            const message = error.response?.data || "An error occurred while creating the note";
            toast.add(message, "error");
        });
    }

    return (
        <div className=" h-full w-full items-center place-content-center">
            {note &&
                <NoteForm
                    folderId={folderId}
                    note={note}
                    onClick={handleCreateNote}
                />
            }
            {!note &&
                <NoteForm
                    folderId={folderId}
                    onClick={handleCreateNote}
                />
            }
        </div>
    );
}