package com.nicovegasr.auth_service.unit_test.usecases;

import com.nicovegasr.auth_service.auth.application.exceptions.UsernameAlreadyExist;
import com.nicovegasr.auth_service.auth.application.usecases.RegisterUser;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterUserTest {
    /**
     * Use cases:
     * 1. User that exist in database should throw an UsernameAlreadyExist exception.
     * 2. New user should be registered.
     */
    UserRepository userRepository;
    String username = "TestUsername";
    String email = "test@email.com";
    String password = "ThisIsMyPassword123+";

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
    }

    @Test
    void registerUserThatAlreadyExistShouldThrowAnError() {
        when(userRepository.existsByUsername(username)).thenReturn(true);
        assertThrows(UsernameAlreadyExist.class, () ->
                RegisterUser.register(userRepository, username, email, password)
        );
    }

    @Test
    void registerNewUserShouldRegisterUser() {
        when(userRepository.existsByUsername(username)).thenReturn(false);
        assertDoesNotThrow(() -> RegisterUser.register(userRepository, username, email, password));
    }
}
