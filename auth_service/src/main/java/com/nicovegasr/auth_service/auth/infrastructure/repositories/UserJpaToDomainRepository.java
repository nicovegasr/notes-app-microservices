package com.nicovegasr.auth_service.auth.infrastructure.repositories;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;
import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;
import com.nicovegasr.auth_service.auth.infrastructure.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserJpaToDomainRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> user = userJpaRepository.findByUsername(username);
        return user.map(userMapper::toDomainModel);
    }

    public User save(User user) {
        return userMapper.toDomainModel(
                userJpaRepository.save(
                        userMapper.toEntity(user)));
    }

    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }
}
