package com.nicovegasr.auth_service.auth.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicovegasr.auth_service.auth.application.usecases.RegisterUser;
import com.nicovegasr.auth_service.auth.infrastructure.api.requests.RegisterRequest;
import com.nicovegasr.auth_service.auth.infrastructure.repositories.UserJpaToDomainRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserJpaToDomainRepository userRepository;

    @PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody RegisterRequest credentials) {
        RegisterUser.register(
                userRepository,
                credentials.username(),
                credentials.password());
        return ResponseEntity.ok().build();
    }
}
