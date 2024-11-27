package com.ecoFin.EcoFin.service;

import com.ecoFin.EcoFin.domain.user.dto.UserLoginResponseDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserRequestDTO;
import com.ecoFin.EcoFin.domain.user.dto.UserResponseDTO;
import com.ecoFin.EcoFin.domain.user.entity.User;
import com.ecoFin.EcoFin.infra.security.TokenService;
import com.ecoFin.EcoFin.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder encoder;

    public List<UserResponseDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::fromUser)
                .collect(Collectors.toList());
    }

    public Optional<User> getById(long id) {
        if (!repository.existsById(id)) return Optional.empty();
        return repository.findById(id);
    }

    public boolean save(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);

        return true;
    }

    public Optional<User> updateById(long id, UserRequestDTO request) {
        if(!repository.existsById(id)) return Optional.empty();

        if (repository.findByEmail(request.getEmail()).isEmpty()) return Optional.empty();

        User userDB = repository.findByEmail(request.getEmail()).get();
        User user = UserRequestDTO.newUser(request);

        if (!user.getEmail().equals(userDB.getEmail())) return Optional.empty();

        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));

        return Optional.of(repository.save(user));
    }

    public boolean deleteById(long id) {
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    public UserLoginResponseDTO login(UserRequestDTO request) {
        Optional<User> userByEmail = repository.findByEmail(request.getEmail());
        if (userByEmail.isEmpty()) return null;

        User user = userByEmail.get();
        if (!encoder.matches(request.getPassword(), user.getPassword())) return null;

        String token = tokenService.generateToken(request);

        return UserLoginResponseDTO.fromUser(user, token);
    }
}
