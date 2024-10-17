package com.ecoFin.EcoFin.service.user;

import com.ecoFin.EcoFin.domain.user.dto.UserRequestDTO;
import com.ecoFin.EcoFin.domain.user.entity.User;
import com.ecoFin.EcoFin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public ResponseEntity<?> saveUser(User data) {
        repository.save(data);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateUserById(Long id, User data) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        data.setId(id);
        repository.save(data);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> deleteUserById(Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
