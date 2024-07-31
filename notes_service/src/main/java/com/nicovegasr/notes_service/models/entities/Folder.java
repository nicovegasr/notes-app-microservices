package com.nicovegasr.notes_service.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "folders")
public class Folder {
    @Id
    private String folderId;
    private String name;
    private String username;
}