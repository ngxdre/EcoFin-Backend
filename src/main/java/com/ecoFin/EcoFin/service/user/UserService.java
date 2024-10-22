package com.ecoFin.EcoFin.service.user;

import com.ecoFin.EcoFin.domain.user.dto.UserRequestDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserResponseDTO;
import com.ecoFin.EcoFin.domain.user.entity.User;
import com.ecoFin.EcoFin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserResponseDTO> listAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::fromUser)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Long id) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }

        return repository.findById(id);
    }

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Optional<User> updateUserById(Long id, User user) {
        if(!repository.existsById(id)) {
            return Optional.empty();
        }

        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));

        return Optional.of(repository.save(user));
    }

    public boolean deleteUserById(Long id) {
        if(!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
