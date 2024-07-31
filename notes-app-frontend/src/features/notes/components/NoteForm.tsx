import { Note } from "@/src/models/Note";
import { CalendarIcon } from '@heroicons/react/24/outline';
import { Button, Card, CardBody, Chip, Input, Textarea } from "@nextui-org/react";
import { useState } from "react";
import { Reminder } from "../../../models/Reminder";

interface NoteFormParams {
    folderId: string;
    note?: Note;
    onClick: (note: Note) => void;
}

export const NoteForm = ({ note, folderId, onClick }: NoteFormParams) => {
    const [noteForm, setNoteForm] = useState<Note>(note || {
        noteId: "",
        folderId: folderId,
        title: "",
        content: "",
        reminders: [],
    });

    const [reminderText, setReminderText] = useState("");
    const [reminderDate, setReminderDate] = useState("");

    const handleInputChange = (field: keyof Note, value: string) => {
        setNoteForm(prev => ({ ...prev, [field]: value }));
    };

    const addReminder = () => {
        if (reminderText && reminderDate) {
            setNoteForm(prev => ({
                ...prev,
                reminders: [...prev.reminders as Reminder[], { text: reminderText, date: reminderDate } as Reminder]
            }));
            setReminderText("");
            setReminderDate("");
        }
    };

    const removeReminder = (index: number) => {
        setNoteForm(prev => ({
            ...prev,
            reminders: prev.reminders?.filter((_, i) => i !== index)
        }));
    };

    const handleSubmit = () => {
        onClick(noteForm);
    };

    const truncateReminderText = (text: string) => {
        if (text && text.length > 10) {
            return text.slice(0, 10) + "...";
        }
        return text;
    }

    return (
        <Card className="w-full max-w-3xl mx-auto">
            <CardBody className="flex flex-col gap-4 p-8">
                <Input
                    placeholder="Note title"
                    value={noteForm.title}
                    onChange={(e) => handleInputChange("title", e.target.value)}
                    className="text-2xl font-serif"
                    size="lg"
                    classNames={{
                        input: "text-2xl font-serif",
                        label: "text-xl"
                    }}
                />
                <Textarea
                    placeholder="Write your note here"
                    value={noteForm.content}
                    onChange={(e) => handleInputChange("content", e.target.value)}
                    minRows={5}
                    size="lg"
                />
                <div className="flex flex-col gap-4">
                    <p className="text-lg font-semibold">Reminders</p>
                    <div className="flex gap-2">
                        <Input
                            placeholder="Reminder text"
                            value={reminderText}
                            onChange={(e) => setReminderText(e.target.value)}
                            size="lg"
                        />
                        <Input
                            type="datetime-local"
                            placeholder="Reminder date"
                            value={reminderDate}
                            onChange={(e) => setReminderDate(e.target.value)}
                            startContent={<CalendarIcon className="w-5 h-5" />}
                            size="lg"
                        />
                        <Button color="primary" size="lg" onClick={addReminder}>Add</Button>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-2">
                        {noteForm.reminders?.map((reminder, index) => (
                            <Chip
                                key={index}
                                onClose={() => removeReminder(index)}
                                variant="flat"
                                size="lg"
                            >
                                {truncateReminderText(reminder.text)} - {new Date(reminder.date).toLocaleString()}
                            </Chip>
                        ))}
                    </div>
                </div>
                <Button color="success" size="lg" onClick={handleSubmit}>
                    Save
                </Button>
            </CardBody>
        </Card>
    );
};