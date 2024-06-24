package com.nicovegasr.notes_service.models.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Folder {
    private Long folderId;
    private String name;
    private List<Note> notes;
}
