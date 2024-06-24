package com.nicovegasr.notes_service.models.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Note {
    private Long noteId;
    private String title;
    private String content;
    private List<Reminder> reminders;
    private String createdAt;
    private String updatedAt;
}
