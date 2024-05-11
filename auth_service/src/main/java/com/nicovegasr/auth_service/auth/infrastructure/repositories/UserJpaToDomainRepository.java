package com.nicovegasr.auth_service.auth.infrastructure.repositories;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserRepository;
import com.nicovegasr.auth_service.auth.infrastructure.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserJpaToDomainRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.toDomainModel(userJpaRepository.findByUsername(username));
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
