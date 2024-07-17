package com.nicovegasr.notes_service.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nicovegasr.notes_service.controllers.requests.CreateNote;
import com.nicovegasr.notes_service.models.entities.Folder;
import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.models.entities.Note;
import com.nicovegasr.notes_service.models.entities.Reminder;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.LayoutRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
@CrossOrigin
public class NotesController {
        private final LayoutRepository layoutRepository;

        @PostMapping("")
        public ResponseEntity<String> createNote(@RequestBody CreateNote noteRequest) {
                Username username = Username.create(noteRequest.username());
                Layout userLayout = layoutRepository.findById(username.getValue())
                                .orElseThrow(() -> new RuntimeException("User layout not found"));

                Folder folder = userLayout.getFolders().stream()
                                .filter(folderToGet -> folderToGet.getFolderId().equals(noteRequest.folderId()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Folder not found"));

                List<Reminder> reminders = noteRequest.reminders().stream()
                                .map(reminder -> Reminder.builder()
                                                .reminderId(UUID.randomUUID().toString())
                                                .date(reminder.getDate())
                                                .text(reminder.getText())
                                                .build())
                                .collect(Collectors.toList());

                Note note = Note.builder()
                                .noteId(UUID.randomUUID().toString())
                                .title(noteRequest.title())
                                .content(noteRequest.content())
                                .reminders(reminders)
                                .build();

                folder.getNotes().add(note);

                layoutRepository.save(userLayout);

                return ResponseEntity.ok().body("Folder created");
        }

        @DeleteMapping("/{noteId}")
        public ResponseEntity<String> deleteNote(@PathVariable String noteId, @RequestParam String username, @RequestParam String folderId) {
                Username usernameVo = Username.create(username);
                Layout userLayout = layoutRepository.findById(usernameVo.getValue())
                                .orElseThrow(() -> new RuntimeException("User layout not found"));
                Folder folder = userLayout.getFolders().stream()
                                .filter(folderToGet -> folderToGet.getFolderId().equals(folderId))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Folder not found"));
                folder.setNotes(folder.getNotes().stream()
                                .filter(note -> !note.getNoteId().equals(noteId))
                                .collect(Collectors.toList()));
                layoutRepository.save(userLayout);
                return ResponseEntity.ok().body("Note deleted");
        }

}
