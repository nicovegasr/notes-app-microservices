package com.nicovegasr.notes_service.exceptions.username;

import com.nicovegasr.notes_service.exceptions.UserException;

public class UsernameEmpty extends UserException {
    public UsernameEmpty() {
        super("Username cannot be null or empty");
    }
}
