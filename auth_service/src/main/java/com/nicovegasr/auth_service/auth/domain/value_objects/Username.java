package com.nicovegasr.auth_service.auth.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Username {
    String name;

    public static Username build(String name) {
        return new Username(name);
    }
}
