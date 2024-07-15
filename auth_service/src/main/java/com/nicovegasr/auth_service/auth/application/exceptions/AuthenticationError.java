package com.nicovegasr.auth_service.auth.application.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError(String message) {
        super(message);
    }
}