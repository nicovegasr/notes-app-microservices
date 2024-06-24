package com.nicovegasr.notes_service.models.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String username;
    private String email;
    private List<Folder> folders;
}
