package com.nicovegasr.notes_service.models.value_objects;

import com.nicovegasr.notes_service.exceptions.email.EmailEmpty;
import com.nicovegasr.notes_service.exceptions.email.EmailIncorrectFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Email {
    private String value;

    public static Email create(String email) {
        checkIsNullOrEmpty(email);
        checkIsCorrectFormat(email);
        return new Email(email);
    }

    private static void checkIsNullOrEmpty(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmailEmpty();
        }
    }

    private static void checkIsCorrectFormat(String email) {
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new EmailIncorrectFormat();
        }
    }
}