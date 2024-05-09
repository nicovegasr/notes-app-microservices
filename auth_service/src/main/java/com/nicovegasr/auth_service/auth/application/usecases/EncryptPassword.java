package com.nicovegasr.auth_service.auth.application.usecases;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.nicovegasr.auth_service.auth.application.exceptions.AlgorithmError;
import com.nicovegasr.auth_service.auth.application.exceptions.SecretKeyNotProvided;
import com.nicovegasr.auth_service.auth.domain.exceptions.password.PasswordEmpty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EncryptPassword {
    public static String encrypt(String password, String secretKey, String cipherAlgorithm) {
        validateEncryptParameters(password, secretKey, cipherAlgorithm);
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), cipherAlgorithm);
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new AlgorithmError(e.getMessage());
        }
    }

    private static void validateEncryptParameters(String password, String secretKey, String cipherAlgorithm) {
        if (password == null || password.isEmpty()) {
            throw new PasswordEmpty();
        }
        if (secretKey == null || secretKey.isEmpty()) {
            throw new SecretKeyNotProvided();
        }
        if (cipherAlgorithm == null || cipherAlgorithm.isEmpty()) {
            throw new AlgorithmError("cipher algorithm not provided");
        }
    }
}
