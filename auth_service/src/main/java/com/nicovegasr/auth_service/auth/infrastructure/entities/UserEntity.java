package com.nicovegasr.auth_service.auth.infrastructure.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "notes")
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "password", nullable = false)
    String password;
    
    @Column(name = "created_at", nullable = false)
    LocalDate createdAt;

    @Column(name = "last_login_date", nullable = false)
    LocalDate lastLoginDate;
}