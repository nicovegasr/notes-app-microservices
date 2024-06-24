package com.nicovegasr.notes_service.models.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document(collection = "layouts")
public class Layout {
    @Id
    private String username;
    private String email;
    private Set<Folder> folders;
}
