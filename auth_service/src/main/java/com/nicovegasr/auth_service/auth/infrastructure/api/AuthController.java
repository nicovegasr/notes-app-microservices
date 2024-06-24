package com.nicovegasr.auth_service.auth.infrastructure.api;

import com.nicovegasr.auth_service.auth.application.usecases.LoginUserWithCredentials;
import com.nicovegasr.auth_service.auth.infrastructure.api.requests.LoginUserRequest;
import com.nicovegasr.auth_service.auth.infrastructure.api.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nicovegasr.auth_service.auth.application.usecases.RegisterUser;
import com.nicovegasr.auth_service.auth.infrastructure.api.requests.RegisterRequest;
import com.nicovegasr.auth_service.auth.infrastructure.repositories.UserJpaToDomainRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserJpaToDomainRepository userRepository;
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody RegisterRequest credentials) {
        RegisterUser.register(
                userRepository,
                credentials.username(),
                credentials.email(),
                credentials.password());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> loginUser(@RequestBody LoginUserRequest credentials) {
        return ResponseEntity.ok().body(
                LoginUserWithCredentials.login(
                        userRepository,
                        credentials.username(),
                        credentials.password(),
                        jwtSecretKey
                )
        );
    }
}
