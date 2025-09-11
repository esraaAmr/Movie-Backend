package com.example.movie.service;

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
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(userMapper::toDto);
    }

}
