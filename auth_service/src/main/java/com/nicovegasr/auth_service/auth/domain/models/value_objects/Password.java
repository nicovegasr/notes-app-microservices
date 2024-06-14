package com.nicovegasr.auth_service.auth.domain.models.value_objects;

import com.nicovegasr.auth_service.auth.domain.exceptions.password.PasswordEmpty;
import com.nicovegasr.auth_service.auth.domain.exceptions.password.PasswordIncorrectFormat;

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
            throw new PasswordIncorrectFormat(); 
        }
    }

    private static void checkIsNullOrEmpty(String password) {
        if (password == null || password.isEmpty()) {
            throw new PasswordEmpty();
        }
    }

}
