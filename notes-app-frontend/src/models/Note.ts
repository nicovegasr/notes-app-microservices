import { Reminder } from "./Reminder";

export interface Note {
    noteId: string;
    title: string;
    content: string;
    folderId: string;
    username?: string;
    createdAt?: string;
    updatedAt?: string;
    reminders?: Reminder[];
}
