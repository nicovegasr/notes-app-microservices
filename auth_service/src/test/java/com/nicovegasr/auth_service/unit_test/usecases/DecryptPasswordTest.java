package com.nicovegasr.auth_service.unit_test.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.nicovegasr.auth_service.auth.application.usecases.DecryptPassword;
import com.nicovegasr.auth_service.auth.application.usecases.EncryptPassword;

class DecryptPasswordTest {
    /**
     * 1.- Decrypt password with null encrypted password should throw an
     * PasswordEmpty error.
     * 2.- Decrypt password with empty encrypted password should throw an
     * PasswordEmpty error.
     * 
     */
    @Test
    void decrypt() {
        String password = "TestTestTest1+";
        String encryptedPassword = EncryptPassword.encrypt(password, "ThisIsASecretKey", "AES");

        String decryptedPassword = DecryptPassword.decrypt(encryptedPassword, "ThisIsASecretKey", "AES");
        assertNotEquals(password, encryptedPassword);
        assertEquals(password, decryptedPassword);
    }
    
}
