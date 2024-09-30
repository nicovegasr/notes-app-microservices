package com.nicovegasr.users_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicovegasr.users_service.models.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
