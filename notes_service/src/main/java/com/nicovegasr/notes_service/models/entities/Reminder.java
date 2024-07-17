package com.nicovegasr.notes_service.models.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reminder {
    private String reminderId;
    private String date;
    private String text;
}
