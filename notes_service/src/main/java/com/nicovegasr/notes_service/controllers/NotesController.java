package com.nicovegasr.notes_service.controllers;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nicovegasr.notes_service.controllers.requests.CreateNote;
import com.nicovegasr.notes_service.controllers.requests.UpdateNote;
import com.nicovegasr.notes_service.models.dtos.DetailedNote;
import com.nicovegasr.notes_service.models.entities.Note;
import com.nicovegasr.notes_service.models.entities.Reminder;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.NoteRepository;
import com.nicovegasr.notes_service.repositories.ReminderRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
@CrossOrigin
public class NotesController {
    private final ReminderRepository reminderRepository;
    private final NoteRepository noteRepository;

    @GetMapping("/{noteId}")
    public ResponseEntity<DetailedNote> getNoteDetails(@PathVariable String noteId) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null) {
            return null;
        }
        List<Reminder> reminders = reminderRepository.findByNoteId(noteId);

        DetailedNote detailedNote = DetailedNote.builder()
                .noteId(note.getNoteId())
                .title(note.getTitle())
                .content(note.getContent())
                .folderId(note.getFolderId())
                .username(note.getUsername())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .reminders(reminders)
                .build();

        return ResponseEntity.ok(detailedNote);
    }

    @PostMapping("")
    public ResponseEntity<String> createNote(@RequestBody CreateNote noteRequest) {
        Username username = Username.create(noteRequest.username());

        Note note = Note.builder()
                .noteId(UUID.randomUUID().toString())
                .title(noteRequest.title())
                .content(noteRequest.content())
                .folderId(noteRequest.folderId())
                .username(username.getValue())
                .createdAt(OffsetDateTime.now().toString())
                .updatedAt(OffsetDateTime.now().toString())
                .build();

        List<Reminder> reminders = noteRequest.reminders().stream()
                .map(reminder -> Reminder.builder()
                        .reminderId(UUID.randomUUID().toString())
                        .noteId(note.getNoteId())
                        .date(reminder.getDate())
                        .text(reminder.getText())
                        .build())
                .collect(Collectors.toList());

        noteRepository.save(note);
        reminderRepository.saveAll(reminders);

        return ResponseEntity.ok().body("Note created");
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<String> updateNote(@PathVariable String noteId, @RequestBody UpdateNote updateNote) {
        Note existingNote = noteRepository.findById(noteId).orElse(null);
        if (existingNote == null) {
            return ResponseEntity.notFound().build();
        }
        existingNote.setTitle(updateNote.title());
        existingNote.setContent(updateNote.content());
        existingNote.setFolderId(updateNote.folderId());
        existingNote.setUpdatedAt(OffsetDateTime.now().toString());

        noteRepository.save(existingNote);

        if (updateNote.reminders() != null) {
            reminderRepository.deleteByNoteId(noteId);
            List<Reminder> newReminders = updateNote.reminders().stream()
                    .map(reminder -> Reminder.builder()
                            .reminderId(UUID.randomUUID().toString())
                            .noteId(noteId)
                            .date(reminder.date())
                            .text(reminder.text())
                            .build())
                    .collect(Collectors.toList());

            reminderRepository.saveAll(newReminders);
        }

        return ResponseEntity.ok().body("Note updated");
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable String noteId) {
        noteRepository.deleteById(noteId);
        return ResponseEntity.ok().body("Note deleted");
    }

}
