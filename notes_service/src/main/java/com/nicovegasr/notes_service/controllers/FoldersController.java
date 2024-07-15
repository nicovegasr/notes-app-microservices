package com.nicovegasr.notes_service.controllers;

import java.util.ArrayList;
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
import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.models.entities.Note;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.LayoutRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/folders")
@RequiredArgsConstructor
@CrossOrigin
public class FoldersController {
    private final LayoutRepository layoutRepository;

    @PostMapping("")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolder folderRequest) {
        Username username = Username.create(folderRequest.user());
        Layout userLayout = layoutRepository.findById(username.getValue())
                .orElseThrow(() -> new RuntimeException("User layout not found"));

        Folder folder = Folder.builder()
                .folderId(UUID.randomUUID().toString())
                .name(folderRequest.name())
                .notes(new ArrayList<>())
                .build();

        userLayout.getFolders().add(folder);
        layoutRepository.save(userLayout);

        return ResponseEntity.ok().body("Folder created");
    }

    @GetMapping("/{folderId}/notes")
    public ResponseEntity<List<Note>> getFolderNoyes(@PathVariable String folderId, @RequestParam String username) {
        Layout userLayout = layoutRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User layout not found"));
        Folder folder = userLayout.getFolders().stream()
                .filter(folderToGet -> folderToGet.getFolderId().equals(folderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Folder not found"));
        return ResponseEntity.ok().body(folder.getNotes());
    }
}
