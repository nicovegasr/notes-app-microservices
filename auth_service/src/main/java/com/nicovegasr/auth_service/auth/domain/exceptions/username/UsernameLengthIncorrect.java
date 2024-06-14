package com.nicovegasr.auth_service.auth.domain.exceptions.username;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class UsernameLengthIncorrect extends UserException {
    public UsernameLengthIncorrect() {
        super("Username length should be between 4 and 20 characters");
    }
}
