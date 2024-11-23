package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.transactionType.dto.TransactionTypeRequestDTO;
import com.ecoFin.EcoFin.domain.transactionType.dto.TransactionTypeResponseDTO;
import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import com.ecoFin.EcoFin.service.TransactionTypeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transactionType")
public class TransactionTypeController {
    @Autowired
    private TransactionTypeService service;

    @GetMapping
    public ResponseEntity<List<TransactionTypeResponseDTO>> listAll() {
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{transactionTypeId}")
    public ResponseEntity<?> getById(@PathVariable("transactionTypeId") long id) {
        Optional<TransactionType> transactionType = service.getById(id);
        return transactionType.isPresent() ? ResponseEntity.ok().body(transactionType) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TransactionTypeRequestDTO data) {
        service.save(TransactionTypeRequestDTO.newTransactionType(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{transactionTypeId}")
    public ResponseEntity<Void> updateById(@PathVariable("transactionTypeId") long id, @Valid @RequestBody TransactionTypeRequestDTO data) {
        Optional<TransactionType> transactionType = service.updateById(id, data);
        return transactionType.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{transactionTypeId}")
    public ResponseEntity<Void> deleteById(@PathVariable("transactionTypeId") long id) {
        boolean deleted = service.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
