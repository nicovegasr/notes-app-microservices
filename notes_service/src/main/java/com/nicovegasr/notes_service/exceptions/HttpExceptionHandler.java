package com.nicovegasr.notes_service.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HttpExceptionHandler {
    @ExceptionHandler(LayoutException.class)
    public ResponseEntity<String> handleUserAuthException(LayoutException layoutException) {
        log.error("Handled layout exception:{}", layoutException.getMessage());
        Integer code = getUserExceptionStatus(layoutException);
        return ResponseEntity.status(code).body(layoutException.getMessage());
    }

    private Integer getUserExceptionStatus(LayoutException userException) {
        return switch (userException.getClass().getSimpleName()) {
            case "UsernameEmpty", "UsernameLengthIncorrect", "EmailEmpty", "EmailIncorrectFormat" -> 400;
            default -> 500;
        };
    }
}
