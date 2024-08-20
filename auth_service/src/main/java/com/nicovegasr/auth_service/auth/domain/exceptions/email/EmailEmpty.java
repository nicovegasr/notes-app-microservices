package com.nicovegasr.auth_service.auth.domain.exceptions.email;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class EmailEmpty extends UserException {
    public EmailEmpty() {
        super("Email cannot be null or empty");
    }
}