package com.nicovegasr.auth_service.auth.infrastructure.repositories.user_repositories;

import org.springframework.stereotype.Repository;

import com.nicovegasr.auth_service.auth.domain.models.User;
import com.nicovegasr.auth_service.auth.domain.repositories.UserDomainRepository;
import com.nicovegasr.auth_service.auth.infrastructure.mappers.UserMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserJpaToDomainRepository implements UserDomainRepository {
    private final UserJpaRepository userJpaRepository;
    
    public User findUserByUsername(String username) {
        return UserMapper.userEntityToDomainModel(userJpaRepository.findByUsername(username));
    }

    public User saveUser(User user) {
        return UserMapper.userEntityToDomainModel(userJpaRepository.save(UserMapper.userDomainModelToEntity(user)));
    }
    
}
