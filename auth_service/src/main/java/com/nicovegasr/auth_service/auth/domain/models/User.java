package com.nicovegasr.auth_service.auth.domain.models;

import java.time.LocalDate;

import com.nicovegasr.auth_service.auth.domain.value_objects.Password;
import com.nicovegasr.auth_service.auth.domain.value_objects.Username;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    Username username;
    Password password;
    LocalDate createdAt;
    LocalDate lastLoginDate;

    public static User create(
            String username,
            String password,
            LocalDate createdAt,
            LocalDate lastLoginDate) {
        return new User(
                Username.create(username),
                Password.create(password),
                createdAt,
                lastLoginDate);
    }
}