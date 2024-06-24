package com.nicovegasr.notes_service.controllers;

import com.nicovegasr.notes_service.exceptions.email.EmailEmpty;
import com.nicovegasr.notes_service.exceptions.username.UsernameEmpty;
import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.models.value_objects.Email;
import com.nicovegasr.notes_service.models.value_objects.Username;
import com.nicovegasr.notes_service.services.LayoutService;
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
    private final LayoutService layoutService;

    @GetMapping("")
    public ResponseEntity<Layout> getUserLayout(@RequestParam String username, @RequestParam String email) {
        return ResponseEntity.ok().body(layoutService.getLayout(username, email));

    }

}
