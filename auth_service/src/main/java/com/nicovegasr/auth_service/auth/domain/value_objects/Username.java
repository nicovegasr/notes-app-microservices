package com.nicovegasr.auth_service.auth.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Username {
    String name;

    public static Username create(String name) {
        validateUsername(name);
        return new Username(name);
    }

    private static void validateUsername(String name) {
        checkIsNullOrEmpty(name);
        checkLengthIsValid(name);
    }

    private static void checkIsNullOrEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
    }

    private static void checkLengthIsValid(String name) {
        if (name.length() < 4 || name.length() > 20) {
            throw new IllegalArgumentException("Username length should be between 4 and 20 characters");
        }
    }
}
