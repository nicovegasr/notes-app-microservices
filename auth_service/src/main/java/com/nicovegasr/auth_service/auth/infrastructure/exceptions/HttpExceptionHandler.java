package com.nicovegasr.auth_service.auth.infrastructure.exceptions;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HttpExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserAuthException(UserException userException) {
        log.error("Handled user exception:{}", userException.getMessage());
        Integer code = getUserExceptionStatus(userException);
        return ResponseEntity.status(code).body(userException.getMessage());
    }

    private Integer getUserExceptionStatus(UserException userException) {
        return switch (userException.getClass().getSimpleName()) {
            case "UsernameEmpty", "PasswordEmpty", "UsernameLengthIncorrect", "PasswordIncorrectFormat" -> 400;
            case "AlgorithmError", "SecretKeyNotProvided" -> 503;
            case "UsernameAlreadyExist" -> 409;
            default -> 500;
        };
    }
}
