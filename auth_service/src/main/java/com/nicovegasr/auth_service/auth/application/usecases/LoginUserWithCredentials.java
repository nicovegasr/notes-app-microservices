package com.nicovegasr.auth_service.auth.application.usecases;

import com.nicovegasr.auth_service.auth.application.exceptions.AuthenticationError;
import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;
import com.nicovegasr.auth_service.auth.infrastructure.api.responses.LoginResponse;

import java.util.Optional;

public class LoginUserWithCredentials {
    public static LoginResponse login(UserRepository userRepository, String username, String password, String secretKey) {
         Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new AuthenticationError("User: " + username + " not found");
        }
        if (!user.get().getPassword().getCredential().equals(password)) {
            throw new AuthenticationError("Passwords do not match");
        }
        String token = JwtToken.create(username, password, secretKey);
        String email = user.get().getEmail().getValue();
        return new LoginResponse(username, email, token);
    }
}
