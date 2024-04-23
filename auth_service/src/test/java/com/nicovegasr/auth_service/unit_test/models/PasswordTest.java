package com.nicovegasr.auth_service.unit_test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.nicovegasr.auth_service.auth.domain.value_objects.Password;

class PasswordTest {
    /**
     * Use cases:
     * 1.- Password with empty string should throw an exception.
     * 2.- Password with null value should throw an exception.
     * 3.- Password with length less than 8 should throw an exception.
     * 4.- Password without correct format should throw an exception.
     * 5.- Password with valid length should be created.
     */

    @Test
    void testPasswordWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create("");
        });
    }

    @Test
    void testPasswordWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create(null);
        });
    }

    @Test
    void testPasswordWithLengthLessThan8() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create("test");
        });
    }

    @Test
    void testPasswordWithoutMayusShouldNotBeCreated() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create("this_is_a_test_1+");
        });
    }

    @Test
    void testPasswordWithoutDigitShouldNotBeCreated() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create("this_is_a_test_T+");
        });
    }

    @Test
    void testPasswordWithoutSpecialCharacterShouldNotBeCreated() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create("thisIsATest1");
        });
    }

    @Test
    void testPasswordWithBlankSpacesShouldNotBeCreated() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password.create("thisIs ATest1+");
        });
    }

    @Test
    void testPasswordWithCorrectFormatShouldBeCreated() {
        String passwordToCreate = "thisIsATest1+";

        Password password = Password.create(passwordToCreate);

        assertEquals(password.getCredential(), passwordToCreate);
    }
}