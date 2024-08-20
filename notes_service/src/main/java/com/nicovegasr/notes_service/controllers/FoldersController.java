package com.nicovegasr.notes_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nicovegasr.notes_service.controllers.requests.CreateFolder;
import com.nicovegasr.notes_service.models.entities.Folder;
import com.nicovegasr.notes_service.models.entities.Note;
import com.nicovegasr.notes_service.services.FoldersService;
import com.nicovegasr.notes_service.services.NotesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/folders")
@RequiredArgsConstructor
@CrossOrigin
public class FoldersController {
    private final FoldersService foldersService;
    private final NotesService notesService;

    @PostMapping("")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolder folderRequest) {
        foldersService.createFolder(folderRequest.user(), folderRequest.name());
        return ResponseEntity.ok().body("Folder created");
    }

    @GetMapping("")
    public ResponseEntity<List<Folder>> getFolders(@RequestParam String username) {
        List<Folder> folders = foldersService.getFoldersByUsername(username);
        return ResponseEntity.ok().body(folders);
    }

    @GetMapping("/{folderId}/notes")
    public ResponseEntity<List<Note>> getFolderNotes(@PathVariable String folderId) {
        List<Note> notes = notesService.getNotesByFolderId(folderId);
        return ResponseEntity.ok().body(notes);
    }
}
