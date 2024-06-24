package com.nicovegasr.notes_service.controllers;

import com.nicovegasr.notes_service.models.entities.User;
import com.nicovegasr.notes_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class UserNotesController {
    private final UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<User> getUserNotes(@RequestParam String username, @RequestParam String email) {
        if (username == null || email == null || username.isEmpty() || email.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User userNotes = userRepository.findByUsername(username);
        if (userNotes == null) {
            userNotes = User.builder()
                    .username(username)
                    .email(email)
                    .build();
            userRepository.save(userNotes);
        }
        return ResponseEntity.ok().body(userNotes);
    }

}
