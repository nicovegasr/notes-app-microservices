package com.nicovegasr.users_service.services;

import org.springframework.stereotype.Service;

import com.nicovegasr.users_service.handlers.UserQueryHandler;
import com.nicovegasr.users_service.models.dtos.UserDTO;
import com.nicovegasr.users_service.queries.GetUserQuery;
import com.nicovegasr.users_service.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserQueryHandler {
    private final UserRepository userRepository;

    @Override
    public UserDTO handle(GetUserQuery query) {
        return userRepository.findById(query.getUsername())
                .map(userEntity -> UserDTO.builder()
                        .username(userEntity.username())
                        .email(userEntity.email())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
