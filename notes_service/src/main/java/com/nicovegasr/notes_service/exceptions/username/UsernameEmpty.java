package com.nicovegasr.notes_service.exceptions.username;

import com.nicovegasr.notes_service.exceptions.LayoutException;

public class UsernameEmpty extends LayoutException {
    public UsernameEmpty() {
        super("Username cannot be null or empty");
    }
}
