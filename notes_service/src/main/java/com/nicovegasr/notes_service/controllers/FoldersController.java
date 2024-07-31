package com.nicovegasr.notes_service.controllers;

import java.util.List;
import java.util.UUID;

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
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.FolderRepository;
import com.nicovegasr.notes_service.repositories.NoteRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/folders")
@RequiredArgsConstructor
@CrossOrigin
public class FoldersController {
    private final FolderRepository folderRepository;
    private final NoteRepository noteRepository;

    @PostMapping("")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolder folderRequest) {
        Username username = Username.create(folderRequest.user());

        Folder folder = Folder.builder()
                .username(username.getValue())
                .folderId(UUID.randomUUID().toString())
                .name(folderRequest.name())
                .build();

        folderRepository.save(folder);

        return ResponseEntity.ok().body("Folder created");
    }

    @GetMapping("")
    public ResponseEntity<List<Folder>> getFolders(@RequestParam String username) {
        Username usernameVo = Username.create(username);

        List<Folder> folders = folderRepository.findByUsername(usernameVo.getValue());

        return ResponseEntity.ok().body(folders);
    }
    
    @GetMapping("/{folderId}/notes")
    public ResponseEntity<List<Note>> getFolderNotes(@PathVariable String folderId, @RequestParam String username) {
        Username usernameVo = Username.create(username);

        List<Note> notes = noteRepository.findByUsernameAndFolderId(usernameVo.getValue(), folderId);
        
        return ResponseEntity.ok().body(notes);
    }
}
