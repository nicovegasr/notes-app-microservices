package com.nicovegasr.auth_service.auth.infrastructure.repositories.user_repositories;

import org.springframework.stereotype.Repository;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;
import com.nicovegasr.auth_service.auth.infrastructure.mappers.UserMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserJpaToDomainRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public User findUserByUsername(String username) {
        return UserMapper.toDomainModel(userJpaRepository.findByUsername(username));
    }

    public User saveUser(User user) {
        return UserMapper.toDomainModel(userJpaRepository.save(UserMapper.toEntity(user)));
    }
}
