package com.ecoFin.EcoFin.service;

import com.ecoFin.EcoFin.domain.transaction.dto.TransactionRequestDTO;
import com.ecoFin.EcoFin.domain.transaction.dto.TransactionResponseDTO;
import com.ecoFin.EcoFin.domain.transaction.entity.Transaction;
import com.ecoFin.EcoFin.repository.TransactionRepository;
import com.ecoFin.EcoFin.specification.TransactionSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public List<TransactionResponseDTO> listByUser(long userId, LocalDate date) {
        return repository.findAll(Specification
                    .where(TransactionSpecification.userIs(userId))
                    .and(TransactionSpecification.dateBetween(
                            date.withDayOfMonth(1),
                            date.withDayOfMonth(date.lengthOfMonth())
                        ))
                )
                .stream()
                .map(TransactionResponseDTO::fromTransaction)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> listByUserAndCategory(long userId, long categoryId, LocalDate date) {
        return repository.findAll(Specification
                    .where(TransactionSpecification.userIs(userId))
                    .and(TransactionSpecification.categoryIs(categoryId))
                    .and(TransactionSpecification.dateBetween(
                            date.withDayOfMonth(1),
                            date.withDayOfMonth(date.lengthOfMonth())
                        ))
                )
                .stream()
                .map(TransactionResponseDTO::fromTransaction)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> listByUserAndTransactionType(long userId, long transactionTypeId, LocalDate date) {
        return repository.findAll(Specification
                    .where(TransactionSpecification.userIs(userId))
                    .and(TransactionSpecification.transactionTypeIs(transactionTypeId))
                    .and(TransactionSpecification.dateBetween(
                            date.withDayOfMonth(1),
                            date.withDayOfMonth(date.lengthOfMonth())
                        ))
                )
                .stream()
                .map(TransactionResponseDTO::fromTransaction)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> listByUserAndCategoryAndTransactionType(
            long userId,
            long categoryId,
            long transactionTypeId,
            LocalDate date
    ) {
        return repository.findAll(Specification
                    .where(TransactionSpecification.userIs(userId))
                    .and(TransactionSpecification.categoryIs(categoryId))
                    .and(TransactionSpecification.transactionTypeIs(transactionTypeId))
                    .and(TransactionSpecification.dateBetween(
                            date.withDayOfMonth(1),
                            date.withDayOfMonth(date.lengthOfMonth())
                        ))
                )
                .stream()
                .map(TransactionResponseDTO::fromTransaction)
                .collect(Collectors.toList());
    }

    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    public Optional<Transaction> updateById(long transactionId, TransactionRequestDTO request) {
        if (!repository.existsById(transactionId)) return Optional.empty();

        Transaction transaction = TransactionRequestDTO.newTransaction(request);
        transaction.setId(transactionId);

        return Optional.of(repository.save(transaction));
    }

    public boolean deleteById(long transactionId) {
        if (!repository.existsById(transactionId)) return false;
        repository.deleteById(transactionId);
        return true;
    }

    public boolean deleteAllByUser(long userId) {
        List<Transaction> userTransactions = repository.findAll(
                Specification.where(TransactionSpecification.userIs(userId))
        );
        repository.deleteAll(userTransactions);
        return true;
    }
}
