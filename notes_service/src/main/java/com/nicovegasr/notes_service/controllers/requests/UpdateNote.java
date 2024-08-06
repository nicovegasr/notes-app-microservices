package com.nicovegasr.notes_service.controllers.requests;

import java.util.List;

public record UpdateNote(
    String noteId,
    String title,
    String content,
    String folderId,
    List<UpdateReminder> reminders
) {
    public record UpdateReminder(
        String reminderId,
        String noteId,
        String date,
        String text
    ) {}
}