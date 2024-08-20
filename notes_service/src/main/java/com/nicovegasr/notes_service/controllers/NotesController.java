package com.nicovegasr.notes_service.controllers;

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
import com.nicovegasr.notes_service.services.NotesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
@CrossOrigin
public class NotesController {
    private final NotesService notesService;

    @GetMapping("/{noteId}")
    public ResponseEntity<DetailedNote> getNoteDetails(@PathVariable String noteId) {
        DetailedNote detailedNote = notesService.getNoteDetails(noteId);
        return ResponseEntity.ok(detailedNote);
    }

    @PostMapping("")
    public ResponseEntity<String> createNote(@RequestBody CreateNote noteRequest) {
        notesService.createNote(
                noteRequest.username(),
                noteRequest.folderId(),
                noteRequest.title(),
                noteRequest.content(),
                noteRequest.reminders());
        return ResponseEntity.ok().body("Note created");
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<String> updateNote(@PathVariable String noteId, @RequestBody UpdateNote updateNote) {
        notesService.updateNote(
                noteId,
                updateNote.title(),
                updateNote.content(),
                updateNote.folderId(),
                updateNote.reminders());

        return ResponseEntity.ok().body("Note updated");
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable String noteId) {
        notesService.deleteNoteById(noteId);
        return ResponseEntity.ok().body("Note deleted");
    }

}
