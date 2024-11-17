package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.transaction.dto.TransactionRequestDTO;
import com.ecoFin.EcoFin.domain.transaction.dto.TransactionResponseDTO;
import com.ecoFin.EcoFin.domain.transaction.entity.Transaction;
import com.ecoFin.EcoFin.service.TransactionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/filter-by-user")
    public ResponseEntity<List<TransactionResponseDTO>> listByUser(
            @RequestParam long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(service.listByUser(userId, date));
    }

    @GetMapping("/filter-by-category")
    public ResponseEntity<List<TransactionResponseDTO>> listByUserAndCategory(
            @RequestParam long userId,
            @RequestParam long categoryId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(service.listByUserAndCategory(userId, categoryId, date));
    }

    @GetMapping("/filter-by-transaction-type")
    public ResponseEntity<List<TransactionResponseDTO>> listByUserAndTransactionType(
            @RequestParam long userId,
            @RequestParam long transactionTypeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(service.listByUserAndTransactionType(userId, transactionTypeId, date));
    }

    @GetMapping("/filter-by")
    public ResponseEntity<List<TransactionResponseDTO>> listByUserAndCategoryAndTransactionType(
            @RequestParam long userId,
            @RequestParam long categoryId,
            @RequestParam long transactionTypeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok().body(service.listByUserAndCategoryAndTransactionType(userId, categoryId, transactionTypeId, date));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TransactionRequestDTO data) {
        service.save(TransactionRequestDTO.newTransaction(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> updateById(
            @PathVariable("transactionId") long transactionId,
            @Valid @RequestBody TransactionRequestDTO data
    ) {
        Optional<Transaction> transaction = service.updateById(transactionId, data);
        return transaction.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteById(@PathVariable("transactionId") long transactionId) {
        boolean deleted = service.deleteById(transactionId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("all/user/{userId}")
    public ResponseEntity<Void> deleteAllByUser(@PathVariable("userId") long userId) {
        boolean deleted = service.deleteAllByUser(userId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
