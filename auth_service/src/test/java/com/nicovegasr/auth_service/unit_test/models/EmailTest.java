package com.nicovegasr.auth_service.unit_test.models;

import com.nicovegasr.auth_service.auth.domain.exceptions.email.EmailEmpty;
import com.nicovegasr.auth_service.auth.domain.exceptions.email.EmailIncorrectFormat;
import com.nicovegasr.auth_service.auth.domain.models.value_objects.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {
    /**
     * Use cases:
     * 1. Email with empty string should throw an exception.
     * 2. Email with null value should throw an exception.
     * 4. Email without correct format should throw an exception.
     * 5. Email with valid length should be created.
     */

    @Test
    void testEmailWithEmptyString() {
        assertThrows(EmailEmpty.class, () ->
                Email.create("")
        );
    }

    @Test
    void testEmailWithNullValue() {
        assertThrows(EmailEmpty.class, () ->
                Email.create(null)
        );
    }

    @Test
    void testEmailWithoutCorrectFormat() {
        String emailToCreate = "email.com+";
        assertThrows(EmailIncorrectFormat.class, () ->
                Email.create(emailToCreate)
        );
    }

    @Test
    void testEmailWithCorrectFormatShouldBeCreated() {
        String emailToCreate = "email@email.com";

        Email email = Email.create(emailToCreate);

        assertEquals(email.getValue(), emailToCreate);
    }
}
