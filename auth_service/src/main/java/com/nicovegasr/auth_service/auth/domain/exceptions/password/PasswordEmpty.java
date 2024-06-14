package com.nicovegasr.auth_service.auth.domain.exceptions.password;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class PasswordEmpty extends UserException {
    public PasswordEmpty() {
        super("Password cannot be null or empty");
    }
}
