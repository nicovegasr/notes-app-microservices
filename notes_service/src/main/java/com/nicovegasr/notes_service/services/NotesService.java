package com.nicovegasr.notes_service.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nicovegasr.notes_service.controllers.requests.UpdateNote.UpdateReminder;
import com.nicovegasr.notes_service.models.dtos.DetailedNote;
import com.nicovegasr.notes_service.models.entities.Note;
import com.nicovegasr.notes_service.models.entities.Reminder;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.NoteRepository;
import com.nicovegasr.notes_service.repositories.ReminderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotesService {
    private final NoteRepository noteRepository;
    private final ReminderRepository reminderRepository;

    public List<Note> getNotesByFolderId(String folderId) {
        return noteRepository.findByFolderId(folderId);
    }

    public DetailedNote getNoteDetails(String noteId) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null) {
            throw new IllegalArgumentException("Note not found");
        }
        List<Reminder> reminders = reminderRepository.findByNoteId(noteId);

        return DetailedNote.builder()
                .noteId(note.getNoteId())
                .title(note.getTitle())
                .content(note.getContent())
                .folderId(note.getFolderId())
                .username(note.getUsername())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .reminders(reminders)
                .build();
    }

    public void createNote(
            String username,
            String folderId,
            String noteTitle,
            String noteContent,
            List<Reminder> reminders) {
        Username usernameVo = Username.create(username);

        Note note = Note.builder()
                .noteId(UUID.randomUUID().toString())
                .title(noteTitle)
                .content(noteContent)
                .folderId(folderId)
                .username(usernameVo.getValue())
                .createdAt(OffsetDateTime.now().toString())
                .updatedAt(OffsetDateTime.now().toString())
                .build();

        List<Reminder> remindersToCreate = reminders.stream()
                .map(reminder -> Reminder.builder()
                        .reminderId(UUID.randomUUID().toString())
                        .noteId(note.getNoteId())
                        .date(reminder.getDate())
                        .text(reminder.getText())
                        .build())
                .collect(Collectors.toList());

        noteRepository.save(note);
        reminderRepository.saveAll(remindersToCreate);

    }

    public void updateNote(
            String noteId,
            String noteTitle,
            String noteContent,
            String folderId,
            List<UpdateReminder> reminders) {
        Note existingNote = noteRepository.findById(noteId).orElse(null);
        if (existingNote == null) {
            throw new IllegalArgumentException("Note not found");
        }

        existingNote.setTitle(noteTitle);
        existingNote.setContent(noteContent);
        existingNote.setFolderId(folderId);
        existingNote.setUpdatedAt(OffsetDateTime.now().toString());

        noteRepository.save(existingNote);

        reminderRepository.deleteByNoteId(noteId);

        List<Reminder> newReminders = reminders.stream()
                .map(reminder -> Reminder.builder()
                        .reminderId(UUID.randomUUID().toString())
                        .noteId(noteId)
                        .date(reminder.date())
                        .text(reminder.text())
                        .build())
                .collect(Collectors.toList());

        reminderRepository.saveAll(newReminders);
    }

    public void deleteNoteById(String noteId) {
        noteRepository.deleteById(noteId);
        reminderRepository.deleteByNoteId(noteId);
    }
}
