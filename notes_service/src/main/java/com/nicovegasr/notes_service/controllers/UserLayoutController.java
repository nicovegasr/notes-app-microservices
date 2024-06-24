package com.nicovegasr.notes_service.controllers;

import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/layout")
@RequiredArgsConstructor
public class UserLayoutController {
    private final UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<Layout> getUserLayout(@RequestParam String username, @RequestParam String email) {
        if (username == null || email == null || username.isEmpty() || email.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Layout layoutNotes = userRepository.findByUsername(username);
        
        if (layoutNotes == null) {
            layoutNotes = Layout.builder()
                    .username(username)
                    .email(email)
                    .build();
            userRepository.save(layoutNotes);
        }
        return ResponseEntity.ok().body(layoutNotes);
    }

}
