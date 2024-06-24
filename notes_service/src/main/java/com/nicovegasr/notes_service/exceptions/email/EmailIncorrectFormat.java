package com.nicovegasr.notes_service.exceptions.email;


import com.nicovegasr.notes_service.exceptions.LayoutException;

public class EmailIncorrectFormat extends LayoutException {
    public EmailIncorrectFormat() {
        super("Email has an incorrect format");
    }
}