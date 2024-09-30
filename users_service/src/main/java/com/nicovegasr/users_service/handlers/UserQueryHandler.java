package com.nicovegasr.users_service.handlers;

import com.nicovegasr.users_service.models.dtos.UserDTO;
import com.nicovegasr.users_service.queries.GetUserQuery;

public interface UserQueryHandler {
    public UserDTO handle(GetUserQuery getUserQuery);
}
