package com.nicovegasr.notes_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nicovegasr.notes_service.models.entities.Note;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByFolderId(String folderId);
    List<Note> findByUsernameAndFolderId(String username, String folderId);
}