import { Reminder } from "./Reminder";

export interface Note {
    id: string;
    folderId: string;
    name: string;
    content: string;
    reminders: Reminder[];
    creationDate: string;
}
