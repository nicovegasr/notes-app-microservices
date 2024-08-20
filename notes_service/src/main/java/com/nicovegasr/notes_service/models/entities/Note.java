package com.nicovegasr.notes_service.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "notes")
public class Note {
    @Id
    private String noteId;
    private String title;
    private String content;
    private String folderId;
    private String username;
    private String createdAt;
    private String updatedAt;
}