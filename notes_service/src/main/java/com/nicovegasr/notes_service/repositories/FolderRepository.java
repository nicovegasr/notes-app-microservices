package com.nicovegasr.notes_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nicovegasr.notes_service.models.entities.Folder;

import java.util.List;

public interface FolderRepository extends MongoRepository<Folder, String> {
    List<Folder> findByUsername(String username);
}