package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.user.dto.UserRequestDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserResponseDTO;
import com.ecoFin.EcoFin.repository.UserRepository;
import com.ecoFin.EcoFin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::fromUser)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserRequestDTO data) {
        return service.saveUser(UserRequestDTO.newUser(data));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable("userId") Long userId, @RequestBody UserRequestDTO data) {
        return service.updateUserById(userId, UserRequestDTO.newUser(data));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId) {
        return service.deleteUserById(userId);
    }
}
