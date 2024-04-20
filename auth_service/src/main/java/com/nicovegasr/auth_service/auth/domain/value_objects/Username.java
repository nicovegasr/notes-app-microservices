package com.nicovegasr.auth_service.auth.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Username {
    String name;

    public static Username build(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return new Username(name);
    }
}
