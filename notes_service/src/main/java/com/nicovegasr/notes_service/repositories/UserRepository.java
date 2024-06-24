package com.nicovegasr.notes_service.repositories;

import com.nicovegasr.notes_service.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}