package com.nicovegasr.auth_service.auth.application.exceptions;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class UsernameAlreadyExist extends UserException {
    public UsernameAlreadyExist() {
        super("Username already exist");
    }
}
