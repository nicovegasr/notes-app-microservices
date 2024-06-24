package com.nicovegasr.notes_service.services;

import com.nicovegasr.notes_service.models.entities.Folder;
import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.models.value_objects.Email;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.repositories.LayoutRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class LayoutService {
    private final LayoutRepository layoutRepository;

    public Layout getLayout(String name, String mail) {
        Username username = Username.create(name);
        Email email = Email.create(mail);
        Layout layoutNotes = layoutRepository.findByUsername(username.getValue());
        boolean userDontHaveLayout = layoutNotes == null;
        if (userDontHaveLayout) {
            layoutNotes = createLayout(username, email);
        }
        return layoutNotes;
    }

    private @NotNull Layout createLayout(Username username, Email email) {
        Layout layoutNotes;
        layoutNotes = Layout.builder()
                .username(username.getValue())
                .email(email.getValue())
                .folders(new HashSet<Folder>())
                .build();
        layoutNotes = layoutRepository.save(layoutNotes);
        return layoutNotes;
    }
}
