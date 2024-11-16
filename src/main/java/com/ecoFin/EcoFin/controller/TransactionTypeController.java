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
    public ResponseEntity<List<TransactionTypeResponseDTO>> listAllTransactionTypes() {
        return ResponseEntity.ok().body(service.listAllTransactionTypes());
    }

    @GetMapping("/{transactionTypeId}")
    public ResponseEntity<?> getTransactionTypeById(@PathVariable("transactionTypeId") long transactionTypeId) {
        Optional<TransactionType> transactionType = service.getTransactionTypeById(transactionTypeId);
        return transactionType.isPresent() ? ResponseEntity.ok().body(transactionType) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveTransactionType(@Valid @RequestBody TransactionTypeRequestDTO data) {
        service.saveTransactionType(TransactionTypeRequestDTO.newTransactionType(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{transactionTypeId}")
    public ResponseEntity<Void> updateTransactionTypeById(@PathVariable("transactionTypeId") long transactionTypeId, @Valid @RequestBody TransactionTypeRequestDTO data) {
        Optional<TransactionType> transactionType = service.updateTransactionTypeById(transactionTypeId, data);
        return transactionType.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{transactionTypeId}")
    public ResponseEntity<Void> deleteTransactionTypeById(@PathVariable("transactionTypeId") long transactionTypeId) {
        boolean deleted = service.deleteTransactionTypeById(transactionTypeId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
