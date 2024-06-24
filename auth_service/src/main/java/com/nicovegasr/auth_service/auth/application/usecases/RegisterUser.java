package com.nicovegasr.auth_service.auth.application.usecases;

import java.time.LocalDate;

import com.nicovegasr.auth_service.auth.application.exceptions.EmailAlreadyExist;
import com.nicovegasr.auth_service.auth.application.exceptions.UsernameAlreadyExist;
import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterUser {
    public static void register(UserRepository userRepository, String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExist();
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExist();
        }
        User user = User.create(username, password, email, LocalDate.now(), null);
        userRepository.save(user);
    }
}
