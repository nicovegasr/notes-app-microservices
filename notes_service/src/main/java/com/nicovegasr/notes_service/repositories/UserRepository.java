package com.nicovegasr.notes_service.repositories;

import com.nicovegasr.notes_service.models.entities.Layout;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Layout, String> {
    Layout findByUsername(String username);
}