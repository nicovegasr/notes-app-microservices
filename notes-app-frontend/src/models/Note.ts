import { Reminder } from "./Reminder";

export interface Note {
    noteId: string;
    folderId: string;
    title: string;
    content: string;
    reminders: Reminder[];
    creationDate: string;
    username?: string;
}
