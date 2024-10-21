package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.user.dto.UserRequestDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserResponseDTO;
import com.ecoFin.EcoFin.domain.user.entity.User;
import com.ecoFin.EcoFin.repository.UserRepository;
import com.ecoFin.EcoFin.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        return ResponseEntity.ok().body(service.listAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long id) {
        Optional<User> user = service.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok().body(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDTO data) {
        service.saveUser(UserRequestDTO.newUser(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") Long userId, @Valid @RequestBody UserRequestDTO data) {
        Optional<User> user = service.updateUserById(userId, UserRequestDTO.newUser(data));
        return user.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") Long userId) {
        boolean deleted = service.deleteUserById(userId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
