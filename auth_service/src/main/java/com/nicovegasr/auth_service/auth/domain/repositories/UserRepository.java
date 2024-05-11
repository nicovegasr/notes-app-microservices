package com.nicovegasr.auth_service.auth.domain.repositories;

import com.nicovegasr.auth_service.auth.domain.models.User;

public interface UserRepository {
    User findByUsername(String email);

    User save(User user);

    boolean existsByUsername(String username);
}
