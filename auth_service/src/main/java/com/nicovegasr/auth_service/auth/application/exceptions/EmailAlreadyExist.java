package com.nicovegasr.auth_service.auth.application.exceptions;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class EmailAlreadyExist extends UserException {
    public EmailAlreadyExist() {
        super("Email already exist");
    }
}
