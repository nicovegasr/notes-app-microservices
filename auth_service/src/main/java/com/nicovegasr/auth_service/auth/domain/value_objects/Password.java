package com.nicovegasr.auth_service.auth.domain.value_objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Password {
    String credential;

    public static Password create(String password) {
        validatePassword(password);
        return new Password(password);
    }

    private static void validatePassword(String password) {
        checkIsNullOrEmpty(password);
        checkIsCorrectFormat(password);
    }

    private static void checkIsCorrectFormat(String password) {
        if (!password.matches(
                "^(?=.*[\\d])" +
                        "(?=.*[a-z])" +
                        "(?=.*[A-Z])" +
                        "(?=.*[@#$%^&+=])" +
                        "(?=\\S+$).{8,}$")) {
            throw new IllegalArgumentException(
                    "Password should contain at least one digit, one lowercase letter, one uppercase letter, one special character no whitespace and length should be at least 8 characters");
        }
    }

    private static void checkIsNullOrEmpty(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

}
