package com.nicovegasr.notes_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nicovegasr.notes_service.models.entities.Reminder;

public interface ReminderRepository extends MongoRepository<Reminder, String> {
    List<Reminder> findByNoteId(String noteId);
}