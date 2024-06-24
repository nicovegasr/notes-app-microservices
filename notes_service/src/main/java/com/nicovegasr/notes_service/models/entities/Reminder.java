package com.nicovegasr.notes_service.models.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reminder {
    private Long reminderId;
    private String datetime;
    private String message;
}
