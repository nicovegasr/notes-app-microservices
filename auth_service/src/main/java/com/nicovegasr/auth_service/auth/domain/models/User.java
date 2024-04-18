package com.nicovegasr.auth_service.auth.domain.models;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
     String username;

    String password;
    
    LocalDate createdAt;

    LocalDate lastLoginDate;
}