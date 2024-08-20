package com.nicovegasr.auth_service.auth.infrastructure.api.responses;

public record LoginResponse(
        String username,
        String email,
        String token) {
}
