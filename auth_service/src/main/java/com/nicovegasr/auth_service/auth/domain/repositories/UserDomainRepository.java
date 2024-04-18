package com.nicovegasr.auth_service.auth.domain.repositories;

import com.nicovegasr.auth_service.auth.domain.models.User;

public interface UserDomainRepository {
    User findUserByUsername(String email);
    User saveUser(User user);
}
