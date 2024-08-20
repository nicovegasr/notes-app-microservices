package com.nicovegasr.notes_service.exceptions.username;

import com.nicovegasr.notes_service.exceptions.LayoutException;

public class UsernameLengthIncorrect extends LayoutException {
    public UsernameLengthIncorrect() {
        super("Username length should be between 4 and 20 characters");
    }
}
