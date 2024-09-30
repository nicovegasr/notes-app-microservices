package com.nicovegasr.users_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicovegasr.users_service.handlers.UserQueryHandler;
import com.nicovegasr.users_service.models.dtos.UserDTO;
import com.nicovegasr.users_service.queries.GetUserQuery;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/queries")
@RequiredArgsConstructor
@CrossOrigin
public class UserQueriesController {
    private final UserQueryHandler queryHandler;

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(
                queryHandler.handle(
                        GetUserQuery.builder()
                                .username(username)
                                .build()));
    }
}