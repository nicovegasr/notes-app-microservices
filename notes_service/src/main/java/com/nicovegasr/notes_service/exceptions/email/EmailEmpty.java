package com.nicovegasr.notes_service.exceptions.email;

import com.nicovegasr.notes_service.exceptions.LayoutException;

public class EmailEmpty extends LayoutException {
    public EmailEmpty() {
        super("Email cannot be null or empty");
    }
}