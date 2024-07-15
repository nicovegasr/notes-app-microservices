package com.nicovegasr.notes_service.services;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.models.value_objects.Email;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.LayoutRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LayoutService {
    private final LayoutRepository layoutRepository;

    public Layout getLayout(String name, String mail) {
        Username username = Username.create(name);
        Email email = Email.create(mail);
        Layout layoutNotes = layoutRepository.findById(username.getValue()).orElse(null);
        if (layoutNotes == null) {
            layoutNotes = createLayout(username, email);
        }
        return layoutNotes;
    }

    private @NotNull Layout createLayout(Username username, Email email) {
        Layout layoutNotes;
        layoutNotes = Layout.builder()
                .username(username.getValue())
                .email(email.getValue())
                .folders(new ArrayList<>())
                .build();
        layoutNotes = layoutRepository.save(layoutNotes);
        return layoutNotes;
    }
}
