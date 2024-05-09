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
public class DecryptPassword {

    public static String decrypt(String encryptedPassword, String secretKey, String cipherAlgorithm) {
        validateDecryptParameters(encryptedPassword, secretKey, cipherAlgorithm);
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), cipherAlgorithm);
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new AlgorithmError(e.getMessage());
        }
    }

    private static void validateDecryptParameters(String encryptedPassword, String secretKey, String cipherAlgorithm) {
        if (encryptedPassword == null || encryptedPassword.isEmpty()) {
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
