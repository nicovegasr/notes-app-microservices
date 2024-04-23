package com.nicovegasr.auth_service.auth.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Password {
    String credential;

    public static Password create(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password length should be at least 8 characters");
        }
        if (!password.matches(
                "^(?=.*[\\d])"      +
                "(?=.*[a-z])"       + 
                "(?=.*[A-Z])"       +
                "(?=.*[@#$%^&+=])"  + 
                "(?=\\S+$).{8,}$")
            ) {
            throw new IllegalArgumentException(
                    "Password should contain at least one digit, one lowercase letter, one uppercase letter, one special character and no whitespace");
        }
        return new Password(password);
    }
}
