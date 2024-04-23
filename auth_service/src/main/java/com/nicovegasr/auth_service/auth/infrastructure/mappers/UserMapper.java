package com.nicovegasr.auth_service.auth.infrastructure.mappers;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toDomainModel(UserEntity userEntity) {
        return User.create(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getCreatedAt(),
                userEntity.getLastLoginDate());
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .username(user.getUsername().getName())
                .password(user.getPassword().getCredential())
                .createdAt(user.getCreatedAt())
                .lastLoginDate(user.getLastLoginDate())
                .build();
    }
}
