package com.nicovegasr.notes_service.models.entities;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Folder {
    private String folderId;
    private String name;
    private List<Note> notes;
}
