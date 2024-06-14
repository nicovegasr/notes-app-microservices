package com.nicovegasr.auth_service.auth.infrastructure.mappers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nicovegasr.auth_service.auth.application.usecases.DecryptPassword;
import com.nicovegasr.auth_service.auth.application.usecases.EncryptPassword;
import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserMapper {

    @Value("${cipher.algorithm}")
    private String cipherAlgorithm;

    @Value("${auth.secret.key}")
    private String secretKey;

    public User toDomainModel(UserEntity userEntity) {
        String decryptedPassword = DecryptPassword.decrypt(userEntity.getPassword(), secretKey, cipherAlgorithm);
        return User.create(
                userEntity.getUsername(),
                decryptedPassword,
                userEntity.getCreatedAt(),
                userEntity.getLastLoginDate());
    }

    public UserEntity toEntity(User user) {
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
