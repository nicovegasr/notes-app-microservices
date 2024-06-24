package com.nicovegasr.auth_service.auth.domain.exceptions.email;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class EmailIncorrectFormat extends UserException {
    public EmailIncorrectFormat() {
        super("Email has an incorrect format");
    }
}