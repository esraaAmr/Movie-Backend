package com.example.movie.service;

import com.example.movie.error.exception.AuthenticationFailedException;
import com.example.movie.mapper.UserMapper;
import com.example.movie.model.dto.UserDto;
import com.example.movie.model.entity.User;
import com.example.movie.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<UserDto> loginDto(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new AuthenticationFailedException("Invalid username or password");
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationFailedException("Invalid username or password");
        }

        return Optional.of(userMapper.toDto(user));
    }
}