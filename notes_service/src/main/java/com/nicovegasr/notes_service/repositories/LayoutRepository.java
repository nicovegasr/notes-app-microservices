package com.nicovegasr.notes_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nicovegasr.notes_service.models.entities.Layout;

public interface LayoutRepository extends MongoRepository<Layout, String> {
}