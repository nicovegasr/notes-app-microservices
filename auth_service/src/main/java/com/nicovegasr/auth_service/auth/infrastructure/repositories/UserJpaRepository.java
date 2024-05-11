package com.nicovegasr.auth_service.auth.infrastructure.repositories;

import com.nicovegasr.auth_service.auth.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);
}
