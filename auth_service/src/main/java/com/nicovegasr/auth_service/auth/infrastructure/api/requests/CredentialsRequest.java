package com.nicovegasr.auth_service.auth.infrastructure.api.requests;

public record CredentialsRequest(
        String username,
        String password) {
}
