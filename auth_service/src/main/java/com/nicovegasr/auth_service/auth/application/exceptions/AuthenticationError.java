package com.nicovegasr.auth_service.auth.application.exceptions;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError(String message) {
        super(message);
    }
}