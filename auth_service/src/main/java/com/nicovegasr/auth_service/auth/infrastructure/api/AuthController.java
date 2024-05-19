package com.nicovegasr.auth_service.auth.infrastructure.api;

import com.nicovegasr.auth_service.auth.application.usecases.LoginUserWithCredentials;
import com.nicovegasr.auth_service.auth.infrastructure.api.requests.LoginUserRequest;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody RegisterRequest credentials) {
        RegisterUser.register(
                userRepository,
                credentials.username(),
                credentials.password());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody LoginUserRequest credentials) {
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
