package com.nicovegasr.notes_service.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "reminders")
public class Reminder {
    @Id
    private String reminderId;
    private String noteId;
    private String date;
    private String text;
}