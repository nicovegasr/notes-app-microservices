package com.nicovegasr.auth_service.auth.infrastructure.mappers;

import org.springframework.beans.factory.annotation.Value;

import com.nicovegasr.auth_service.auth.application.usecases.DecryptPassword;
import com.nicovegasr.auth_service.auth.application.usecases.EncryptPassword;
import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    @Value("${cipher.algorithm}")
    private static String cipherAlgorithm;

    @Value("${secret.key}")
    private static String secretKey;

    public static User toDomainModel(UserEntity userEntity) {
        String decryptedPassword = DecryptPassword.decrypt(userEntity.getPassword(), secretKey, cipherAlgorithm);
        return User.create(
                userEntity.getUsername(),
                decryptedPassword,
                userEntity.getCreatedAt(),
                userEntity.getLastLoginDate());
    }

    public static UserEntity toEntity(User user) {
        String encryptedPassword = EncryptPassword.encrypt(user.getPassword().getCredential(),
                secretKey,
                cipherAlgorithm);
        return UserEntity.builder()
                .username(user.getUsername().getName())
                .password(encryptedPassword)
                .createdAt(user.getCreatedAt())
                .lastLoginDate(user.getLastLoginDate())
                .build();
    }
}
