import { Note } from "./Note";

export interface Folder {
    folderId: string;
    name: string;
    notes: Note[];
}