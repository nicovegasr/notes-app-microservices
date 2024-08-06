import { useLocation, useNavigate } from "react-router-dom";
import { Note } from "../../../models/Note";
import NotesRepository from "../../../repositories/NotesRepository";
import { useToast } from "../../commons/hooks/useToasts";
import { NoteForm } from "../components/NoteForm";

export const NotePage = () => {
    const { state } = useLocation();
    const folderId = state?.folderId;
    const note: Note = state?.note;

    const { createNoteQuery, getNoteDetailsQuery, updateNoteQuery } = NotesRepository({ noteId: note?.noteId });

    const noteToUpdate = getNoteDetailsQuery.data;

    const navigate = useNavigate();
    const toast = useToast();

    const handleCreateNote = async (note: Note) => {
        try {
            await createNoteQuery(note);
            toast.add("Note created successfully", "success");
            navigate("/");
        } catch (error: any) {
            const message = error.response?.data || "An error occurred while creating the note";
            toast.add(message, "error");
        }
    }

    const handleUpdatenote = (note: Note) => {
        updateNoteQuery(note).then(() => {
            toast.add("Note updated successfully", "success");
            navigate("/");
        }).catch((error) => {
            const message = error.response?.data || "An error occurred while updating the note";
            toast.add(message, "error");
        });
    }

    return (
        <div className=" h-full w-full items-center place-content-center">
            {note && noteToUpdate &&
                <NoteForm
                    folderId={note.folderId}
                    note={noteToUpdate}
                    onClick={handleUpdatenote}
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