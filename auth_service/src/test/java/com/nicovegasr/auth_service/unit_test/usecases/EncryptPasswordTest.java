package com.nicovegasr.auth_service.unit_test.usecases;

import com.nicovegasr.auth_service.auth.application.exceptions.AlgorithmError;
import com.nicovegasr.auth_service.auth.application.exceptions.SecretKeyNotProvided;
import com.nicovegasr.auth_service.auth.application.usecases.EncryptPassword;
import com.nicovegasr.auth_service.auth.domain.exceptions.password.PasswordEmpty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncryptPasswordTest {
    /**
     * Use cases:
     * 1. Encrypt password with null encrypted password should throw an PasswordEmpty error.
     * 2. Encrypt password with empty encrypted password should throw an PasswordEmpty error.
     * 3. Encrypt password with null secret key should throw a SecretKeyNotProvided error.
     * 4. Encrypt password with empty secret key should throw a SecretKeyNotProvided error.
     * 5. Encrypt password with null cipher algorithm should throw an AlgorithmError.
     * 6. Encrypt password with empty cipher algorithm should throw an AlgorithmError.
     * 7. Encrypt password with AES and invalid AES secret key should throw an AlgorithmError.
     * 8. Encrypt password with correct arguments should encrypt a password.
     */

    String password;

    @BeforeEach
    void setUp() {
        this.password = "TestTestTest1+";
    }

    @Test
    void encryptPasswordWithNullEncryptedPasswordShouldTrowAnError() {
        assertThrows(PasswordEmpty.class, () ->
                EncryptPassword.encrypt(null, "ThisIsASecretKey", "AES")
        );
    }

    @Test
    void encryptPasswordWithEmptyEncryptedPasswordShouldTrowAnError() {
        assertThrows(PasswordEmpty.class, () ->
                EncryptPassword.encrypt("", "ThisIsASecretKey", "AES")
        );
    }

    @Test
    void encryptPasswordWithNullSecretKeyShouldTrowAnError() {
        assertThrows(SecretKeyNotProvided.class, () ->
                EncryptPassword.encrypt(password, null, "AES")
        );
    }
    @Test
    void encryptPasswordWithEmptySecretKeyShouldTrowAnError() {
        assertThrows(SecretKeyNotProvided.class, () ->
                EncryptPassword.encrypt(password, "", "AES")
        );
    }

    @Test
    void encryptPasswordWithNullCipherAlgorithmShouldTrowAnError() {
        assertThrows(AlgorithmError.class, () ->
                EncryptPassword.encrypt(password, "ThisIsASecretKey", null)
        );
    }

    @Test
    void encryptPasswordWithEmptyCipherAlgorithmShouldTrowAnError() {
        assertThrows(AlgorithmError.class, () ->
                EncryptPassword.encrypt(password, "ThisIsASecretKey", "")
        );
    }

    @Test
    void encryptPasswordWithInvalidAesKeyShouldTrowAnError() {
        assertThrows(AlgorithmError.class, () ->
                EncryptPassword.encrypt(password, "ThisIsAInvalidSecretKey", "AES")
        );
    }

    @Test
    void encryptPassword() {
        String encryptedPassword = EncryptPassword.encrypt(password, "ThisIsASecretKey", "AES");

        assertNotEquals(encryptedPassword, password);
    }

}
