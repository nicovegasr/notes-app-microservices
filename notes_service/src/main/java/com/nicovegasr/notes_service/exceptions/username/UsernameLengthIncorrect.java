package com.nicovegasr.notes_service.exceptions.username;

import com.nicovegasr.notes_service.exceptions.UserException;

public class UsernameLengthIncorrect extends UserException {
    public UsernameLengthIncorrect() {
        super("Username length should be between 4 and 20 characters");
    }
}
