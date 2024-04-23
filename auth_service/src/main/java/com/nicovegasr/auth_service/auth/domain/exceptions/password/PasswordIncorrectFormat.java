package com.nicovegasr.auth_service.auth.domain.exceptions.password;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class PasswordIncorrectFormat extends UserException {
    public PasswordIncorrectFormat() {
        super("Password should contain at least one digit, one lowercase letter, one uppercase letter, one special character no whitespace and length should be at least 8 characters");
    }
}
