package com.ecoFin.EcoFin.service;

import com.ecoFin.EcoFin.domain.transactionType.dto.TransactionTypeRequestDTO;
import com.ecoFin.EcoFin.domain.transactionType.dto.TransactionTypeResponseDTO;
import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import com.ecoFin.EcoFin.repository.TransactionTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionTypeService {
    @Autowired
    private TransactionTypeRepository repository;

    public List<TransactionTypeResponseDTO> listAllTransactionTypes() {
        return repository.findAll()
                .stream()
                .map(TransactionTypeResponseDTO::fromTransactionType)
                .collect(Collectors.toList());
    }

    public Optional<TransactionType> getTransactionTypeById(long id) {
        if (!repository.existsById(id)) return Optional.empty();
        return repository.findById(id);
    }

    public TransactionType saveTransactionType(TransactionType transactionType) {
        return repository.save(transactionType);
    }

    public Optional<TransactionType> updateTransactionTypeById(long id, TransactionTypeRequestDTO request) {
        if (!repository.existsById(id)) return Optional.empty();

        TransactionType transactionType = TransactionTypeRequestDTO.newTransactionType(request);
        transactionType.setId(id);

        return Optional.of(repository.save(transactionType));
    }

    public boolean deleteTransactionTypeById(long id) {
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
