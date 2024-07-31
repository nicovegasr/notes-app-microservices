package com.nicovegasr.notes_service.models.dtos;

import java.util.List;

import com.nicovegasr.notes_service.models.entities.Reminder;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetailedNote {
    private String noteId;
    private String title;
    private String content;
    private String folderId;
    private String username;
    private String createdAt;
    private String updatedAt;
    private List<Reminder> reminders;
}
