package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.users.User;
import com.ecoFin.EcoFin.domain.users.UserRequestDTO;
import com.ecoFin.EcoFin.domain.users.UserResponseDTO;
import com.ecoFin.EcoFin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::fromUser)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void saveUser(@RequestBody UserRequestDTO data) {
        var user = UserRequestDTO.newUser(data);
        repository.save(user);
        return;
    }
}
