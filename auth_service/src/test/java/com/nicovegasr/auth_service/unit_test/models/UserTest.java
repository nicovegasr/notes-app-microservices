package com.nicovegasr.auth_service.unit_test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;
import com.nicovegasr.auth_service.auth.domain.models.User;

class UserTest {
    /**
     * Use cases:
     * 1. User without valid username should throw exception
     * 2. User without valid password should throw exception
     * 3. Create a user with a valid username and password
     */

    @Test
    void userWithoutValidUsernameShouldThrowException() {
        LocalDate now = LocalDate.now();
        assertThrows(UserException.class, () -> {
            User.create("tes", "TestPassword1+", now, now);
        });
    }

    @Test
    void userWithoutValidPasswordShouldThrowException() {
        LocalDate now = LocalDate.now();
        assertThrows(UserException.class, () -> {
            User.create("test", "test", now, now);
        });
    }

    @Test
    void createUserWithValidUsernameAndPassword() {
        LocalDate now = LocalDate.now();
        User user = User.create("test", "TestPassword1+", now, now);

        assertEquals("test", user.getUsername().getName());
        assertEquals("TestPassword1+", user.getPassword().getCredential());
    }

}
