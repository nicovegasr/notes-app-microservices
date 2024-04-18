package com.nicovegasr.auth_service.auth.infrastructure.mappers;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;

public class UserMapper {

    private UserMapper() { }

    public static User userEntityToDomainModel(UserEntity userEntity) {
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .lastLoginDate(userEntity.getLastLoginDate())
                .build();
    }

    public static UserEntity userDomainModelToEntity(User user) {
        return UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .lastLoginDate(user.getLastLoginDate())
                .build();
    }
}
