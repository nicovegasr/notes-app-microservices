package com.nicovegasr.auth_service.auth.domain.models;

import java.time.LocalDate;

import com.nicovegasr.auth_service.auth.domain.models.value_objects.Email;
import com.nicovegasr.auth_service.auth.domain.models.value_objects.Password;
import com.nicovegasr.auth_service.auth.domain.models.value_objects.Username;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Username username;
    private Password password;
    private Email email;
    private LocalDate createdAt;
    private LocalDate lastLoginDate;

    public static User create(
            String username,
            String password,
            String email,
            LocalDate createdAt,
            LocalDate lastLoginDate) {
        return new User(
                Username.create(username),
                Password.create(password),
                Email.create(email),
                createdAt,
                lastLoginDate);
    }
}