package com.nicovegasr.auth_service.auth.infrastructure.repositories.user_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
