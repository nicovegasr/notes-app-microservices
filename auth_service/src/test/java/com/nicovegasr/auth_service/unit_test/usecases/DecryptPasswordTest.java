package com.nicovegasr.auth_service.unit_test.usecases;

import com.nicovegasr.auth_service.auth.application.exceptions.AlgorithmError;
import com.nicovegasr.auth_service.auth.application.exceptions.SecretKeyNotProvided;
import com.nicovegasr.auth_service.auth.application.usecases.DecryptPassword;
import com.nicovegasr.auth_service.auth.application.usecases.EncryptPassword;
import com.nicovegasr.auth_service.auth.domain.exceptions.password.PasswordEmpty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecryptPasswordTest {
    /**
     * Use cases:
     * 1. Decrypt password with null encrypted password should throw an PasswordEmpty error.
     * 2. Decrypt password with empty encrypted password should throw an PasswordEmpty error.
     * 3. Decrypt password with null secret key should throw a SecretKeyNotProvided error.
     * 4. Decrypt password with empty secret key should throw a SecretKeyNotProvided error.
     * 5. Decrypt password with null cipher algorithm should throw an AlgorithmError.
     * 6. Decrypt password with empty cipher algorithm should throw an AlgorithmError.
     * 7. Decrypt password with AES and invalid AES secret key should throw an AlgorithmError.
     * 8. Decrypt password with correct arguments should decrypt a cipher password.
     */

    String password;
    String encryptedPassword;

    @BeforeEach
    void setUp() {
        this.password = "TestTestTest1+";
        this.encryptedPassword = EncryptPassword.encrypt(password, "ThisIsASecretKey", "AES");
    }

    @Test
    void decryptPasswordWithNullEncryptedPasswordShouldTrowAnError() {
        assertThrows(PasswordEmpty.class, () ->
                DecryptPassword.decrypt(null, "ThisIsASecretKey", "AES")
        );
    }

    @Test
    void decryptPasswordWithEmptyEncryptedPasswordShouldTrowAnError() {
        assertThrows(PasswordEmpty.class, () ->
                DecryptPassword.decrypt("", "ThisIsASecretKey", "AES")
        );
    }

    @Test
    void decryptPasswordWithNullSecretKeyShouldTrowAnError() {
        assertThrows(SecretKeyNotProvided.class, () ->
                DecryptPassword.decrypt(encryptedPassword, null, "AES")
        );
    }
    @Test
    void decryptPasswordWithEmptySecretKeyShouldTrowAnError() {
        assertThrows(SecretKeyNotProvided.class, () ->
                DecryptPassword.decrypt(encryptedPassword, "", "AES")
        );
    }

    @Test
    void decryptPasswordWithNullCipherAlgorithmShouldTrowAnError() {
        assertThrows(AlgorithmError.class, () ->
                DecryptPassword.decrypt(encryptedPassword, "ThisIsASecretKey", null)
        );
    }

    @Test
    void decryptPasswordWithEmptyCipherAlgorithmShouldTrowAnError() {
        assertThrows(AlgorithmError.class, () ->
                DecryptPassword.decrypt(encryptedPassword, "ThisIsASecretKey", "")
        );
    }

    @Test
    void decryptPasswordWithInvalidAesKeyShouldTrowAnError() {
        assertThrows(AlgorithmError.class, () ->
                DecryptPassword.decrypt(encryptedPassword, "ThisIsAInvalidSecretKey", "AES")
        );
    }

    @Test
    void decryptPassword() {
        String decryptedPassword = DecryptPassword.decrypt(encryptedPassword, "ThisIsASecretKey", "AES");

        assertNotEquals(password, encryptedPassword);
        assertEquals(password, decryptedPassword);
    }

}
