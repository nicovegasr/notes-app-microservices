import { Folder } from "@/src/models/Folder";
import { Note } from "@/src/models/Note";
import { Button } from "@nextui-org/react";
import NotesRepository from "../../../repositories/NotesRepository";
import { NoteButton } from "../components/NoteButton";

interface NotesLayoutProps {
    folder: Folder;
}

export const NotesLayout = ({ folder }: NotesLayoutProps) => {
    const { getNotesByFolder } = NotesRepository();
    const notesQuery = getNotesByFolder(folder.folderId);
    const notes: Note[] = notesQuery.data || [];

    const folderWithNotes = (): boolean => {
        return (notes && Array.isArray(notes) && notes.length > 0);
    }

    const folderWithoutNotes = (): boolean => {
        return (notes && Array.isArray(notes) && notes.length === 0);
    }

    return (
        <div className="notes-animation w-full bg-white mt-3 p-3 h-full">
            <div className="flex flex-row items-center justify-between ">
                <h3 className="text-2xl font-bold underline">
                    Notes
                </h3>
                <Button
                    color="primary"
                    className="m-4"
                >
                    Create note
                </Button>
            </div>
            {folderWithNotes() &&
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 p-4">
                    {notes
                        .sort((a, b) => new Date(b.creationDate).getTime() - new Date(a.creationDate).getTime())
                        .map((note) => (
                            <NoteButton
                                note={note}
                                onNoteClick={() => { console.log("hola") }}
                            />
                        ))
                    }
                </div>
            }
            {folderWithoutNotes() &&
                <div className="h-full w-full flex flex-col items-center justify-center">
                    <div className="text-center text-lg font-bold text-gray-500 items-center justify-center">
                        There are no notes in this folder
                    </div>
                </div>
            }

        </div>
    );
}