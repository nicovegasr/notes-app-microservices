package com.nicovegasr.auth_service.unit_test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;

class RegisterUserTest {
    /**
     * 1.- User with vaild credentials (username & password) should be created.
     * 2.- User that already exist should not be created.
     * 3.- User with invalid username should not be created.
     * 4.- User with invalid password should not be created.
     */
    UserRepository userRepository;

    @Test
    void userWithValidCredentialsShouldBeCreated() {

    }
}
