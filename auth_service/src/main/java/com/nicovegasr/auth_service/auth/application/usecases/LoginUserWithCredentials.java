package com.nicovegasr.auth_service.auth.application.usecases;

import com.nicovegasr.auth_service.auth.application.exceptions.AuthenticationError;
import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;

import java.util.Optional;

public class LoginUserWithCredentials {
    public static String login(UserRepository userRepository, String username, String password, String secretKey) {
         Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new AuthenticationError("User: " + username + " not found");
        }
        if (!user.get().getPassword().getCredential().equals(password)) {
            throw new AuthenticationError("Passwords do not match");
        }
        return JwtToken.create(username, password, secretKey);
    }
}
