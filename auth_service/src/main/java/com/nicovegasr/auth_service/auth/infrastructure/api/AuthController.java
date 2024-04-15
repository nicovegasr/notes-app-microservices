package com.nicovegasr.auth_service.auth.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicovegasr.auth_service.auth.infrastructure.api.requests.CredentialsRequest;
import com.nicovegasr.auth_service.auth.infrastructure.api.responses.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login/credentials")
    ResponseEntity<LoginResponse> loginWithCredentials(@RequestBody CredentialsRequest credentials) {
        
        return ResponseEntity.ok().build();
    }
}
