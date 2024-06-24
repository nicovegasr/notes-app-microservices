package com.nicovegasr.auth_service.auth.infrastructure.api.requests;

public record RegisterRequest(
        String username,
        String email,
        String password) {
}
