package com.nicovegasr.auth_service.auth.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicovegasr.auth_service.auth.infrastructure.api.requests.RegisterRequest;
import com.nicovegasr.auth_service.auth.infrastructure.api.responses.LoginResponse;
import com.nicovegasr.auth_service.auth.infrastructure.repositories.user_repositories.UserJpaToDomainRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserJpaToDomainRepository userRepository;

    @PostMapping("/register")
    ResponseEntity<LoginResponse> registerUser(@RequestBody RegisterRequest credentials) {

        return ResponseEntity.ok().build();
    }
}
