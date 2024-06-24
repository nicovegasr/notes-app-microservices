package com.nicovegasr.notes_service.unit_tests.models;


import com.nicovegasr.notes_service.exceptions.username.UsernameEmpty;
import com.nicovegasr.notes_service.exceptions.username.UsernameLengthIncorrect;
import com.nicovegasr.notes_service.models.value_objects.Username;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsernameTest {
    /**
     * Use cases:
     * 1. Username with empty string should throw an exception
     * 2. Username with null value should throw an exception
     * 3. Username with name length less than 4 should throw an exception
     * 4. Username with name length greater than 20 should throw an exception
     * 5. Username with valid name should be created.
     */
    @Test
    void testUsernameWithEmptyString() {
        assertThrows(UsernameEmpty.class, () ->
            Username.create("")
        );
    }

    @Test
    void testUsernameWithNullValue() {
        assertThrows(UsernameEmpty.class, () ->
            Username.create(null)
        );
    }

    @Test
    void testUsernameWithLengthLessThan4() {
        assertThrows(UsernameLengthIncorrect.class, () ->
            Username.create("tes")
        );
    }

    @Test
    void testUsernameWithLengthGreaterThan20() {
        assertThrows(UsernameLengthIncorrect.class, () ->
            Username.create("test_greather_than_20_characters")
        );
    }

    @Test
    void testUsernameWithValidName() {
        String usernameToCreate = "test";

        Username username = Username.create(usernameToCreate);

        assertEquals(username.getValue(), usernameToCreate);
    }
}
