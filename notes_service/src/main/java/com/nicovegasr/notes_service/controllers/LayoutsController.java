package com.nicovegasr.notes_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nicovegasr.notes_service.models.entities.Layout;
import com.nicovegasr.notes_service.services.LayoutService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/layouts")
@RequiredArgsConstructor
public class LayoutsController {
    private final LayoutService layoutService;
    
    @GetMapping("")
    public ResponseEntity<Layout> getUserLayout(@RequestParam String username, @RequestParam String email) {
        return ResponseEntity.ok().body(layoutService.getLayout(username, email));

    }

}
