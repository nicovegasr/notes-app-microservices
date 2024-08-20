package com.nicovegasr.notes_service.controllers.requests;

import java.util.List;

import com.nicovegasr.notes_service.models.entities.Reminder;

public record CreateNote (
    String username,
    String folderId,
    String title,
    String content,
    List<Reminder> reminders
) {}
