package com.nicovegasr.auth_service.auth.domain.models.value_objects;

import com.nicovegasr.auth_service.auth.domain.exceptions.username.UsernameEmpty;
import com.nicovegasr.auth_service.auth.domain.exceptions.username.UsernameLengthIncorrect;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
            throw new UsernameEmpty();
        }
    }

    private static void checkLengthIsValid(String name) {
        if (name.length() < 4 || name.length() > 20) {
            throw new UsernameLengthIncorrect();
        }
    }
}
