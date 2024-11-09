package com.ucamp.movieusAdmin.controller;

import com.ucamp.movieusAdmin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestControler {

    private final UserService userService;

    @GetMapping("/monthly-registrations")
    public ResponseEntity<Map<String, Long>> getMonthlyRegistrations() {
        Map<String, Long> monthlyRegistrations = userService.getMonthlyUserRegistrations();
        return ResponseEntity.ok(monthlyRegistrations);
    }
}
