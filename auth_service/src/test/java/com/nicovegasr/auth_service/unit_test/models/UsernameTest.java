package com.nicovegasr.auth_service.unit_test.models;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.nicovegasr.auth_service.auth.domain.value_objects.Username;

class UsernameTest {
    /**
     * Use cases:
     * 1.- Username with empty string should throw an exception
     * 2.- Username with null value should throw an exception
     * 3.- Username with name length less than 4 should throw an exception
     */
    @Test
    void testUsernameWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Username.build("");
        });
    }
}
