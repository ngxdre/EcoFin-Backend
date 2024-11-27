package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.user.dto.UserLoginResponseDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserRequestDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserResponseDTO;
import com.ecoFin.EcoFin.domain.user.entity.User;
import com.ecoFin.EcoFin.service.UserService;

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
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getById(@PathVariable("userId") long id) {
        Optional<User> user = service.getById(id);
        return user.isPresent() ? ResponseEntity.ok().body(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequestDTO data) {
        boolean inserted = service.save(UserRequestDTO.newUser(data));
        return inserted ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateById(@PathVariable("userId") long id, @Valid @RequestBody UserRequestDTO data) {
        Optional<User> user = service.updateById(id, data);
        return user.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") long id) {
        boolean deleted = service.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO user){
        UserLoginResponseDTO response = service.login(user);
        return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(response);
    }
}
