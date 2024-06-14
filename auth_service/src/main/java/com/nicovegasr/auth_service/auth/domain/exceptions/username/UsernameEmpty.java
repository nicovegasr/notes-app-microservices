package com.nicovegasr.auth_service.auth.domain.exceptions.username;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class UsernameEmpty extends UserException {
    public UsernameEmpty() {
        super("Username cannot be null or empty");
    }
}
