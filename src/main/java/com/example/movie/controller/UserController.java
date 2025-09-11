package com.example.movie.controller;

import com.example.movie.model.dto.UserDto;
import com.example.movie.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam String username, @RequestParam String password) {
        return userService.loginDto(username, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
