package com.nicovegasr.users_service.models.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String username;
    private String email;
}