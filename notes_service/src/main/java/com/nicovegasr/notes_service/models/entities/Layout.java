package com.nicovegasr.notes_service.models.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "layouts")
public class Layout {
    @Id
    private String username;
    private String email;
    private List<Folder> folders;
}
