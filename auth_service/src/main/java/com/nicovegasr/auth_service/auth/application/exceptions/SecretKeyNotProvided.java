package com.nicovegasr.auth_service.auth.application.exceptions;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class SecretKeyNotProvided extends UserException {
    public SecretKeyNotProvided() {
        super("Secret key to decrypt password not provided.");
    }
}
