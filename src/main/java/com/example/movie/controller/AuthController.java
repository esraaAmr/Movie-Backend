package com.example.movie.controller;

import com.example.movie.model.entity.User;
import com.example.movie.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword())
                .map(u -> ResponseEntity.ok("Login successful! Welcome " + u.getUsername()))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid username or password"));
    }

}
