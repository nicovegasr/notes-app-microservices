package com.nicovegasr.auth_service.auth.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Username {
    String name;

    public static Username create(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (name.length() < 4) {
            throw new IllegalArgumentException("Username length should be at least 4 characters");
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException("Username length should be at most 20 characters");
        }
        return new Username(name);
    }
}
