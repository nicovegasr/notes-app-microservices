import { Folder } from "@/src/models/Folder";
import { Note } from "@/src/models/Note";
import { Button, Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, useDisclosure } from "@nextui-org/react";
import { useNavigate } from "react-router-dom";
import NotesRepository from "../../../repositories/NotesRepository";
import { useToast } from "../../commons/hooks/useToasts";
import { NoteCard } from "../components/NoteCard";
import { useState } from "react";

interface NotesLayoutProps {
    folder: Folder;
}

export const NotesLayout = ({ folder }: NotesLayoutProps) => {
    const { getNotesByFolder, deleteNoteQuery } = NotesRepository({ folderId: folder.folderId });

    const notes: Note[] = getNotesByFolder.data || [];
    const navigate = useNavigate();
    const toast = useToast();
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [noteToDelete, setNoteToDelete] = useState<Note | null>(null);

    const folderWithNotes = (): boolean => {
        return (notes && Array.isArray(notes) && notes.length > 0);
    }

    const folderWithoutNotes = (): boolean => {
        return (notes && Array.isArray(notes) && notes.length === 0);
    }

    const handleDeleteNote = (note: Note) => {
        setNoteToDelete(note);
        onOpen();
    }

    const confirmDelete = () => {
        if (noteToDelete) {
            const noteWithFolderId = { ...noteToDelete, folderId: folder.folderId };
            deleteNoteQuery(noteWithFolderId).then(() => {
                toast.add("Note deleted successfully", "success");
                onClose();
            }).catch((error) => {
                const message = error.response?.data || "An error occurred while deleting the note";
                toast.add(message, "error");
            });
        }
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
                    onClick={() => navigate("/note", {
                        state: {
                            folderId: folder.folderId
                        }
                    })}
                >
                    Create note
                </Button>
            </div>
            {folderWithNotes() &&
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 p-4">
                    {notes
                        .sort((a, b) => new Date(b.createdAt as string).getTime() - new Date(a.createdAt as string).getTime())
                        .map((note) => (
                            <NoteCard
                                key={note.noteId}
                                note={note}
                                onClick={() => navigate("/note", {
                                    state: {
                                        folderId: folder.folderId,
                                        note: note
                                    }
                                })}
                                onDelete={() => handleDeleteNote(note)}
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
            <Modal isOpen={isOpen} onClose={onClose}>
                <ModalContent>
                    {(onClose) => (
                        <>
                            <ModalHeader className="flex flex-col gap-1">Confirm Deletion</ModalHeader>
                            <ModalBody>
                                <p>Are you sure you want to delete this note?</p>
                            </ModalBody>
                            <ModalFooter>
                                <Button color="danger" variant="light" onPress={onClose}>
                                    Cancel
                                </Button>
                                <Button color="primary" onPress={confirmDelete}>
                                    Confirm
                                </Button>
                            </ModalFooter>
                        </>
                    )}
                </ModalContent>
            </Modal>
        </div>
    );
}