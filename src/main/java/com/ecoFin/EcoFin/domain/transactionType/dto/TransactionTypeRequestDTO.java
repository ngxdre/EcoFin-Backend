package com.ecoFin.EcoFin.domain.transactionType.dto;

import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import lombok.Getter;

@Getter
public class TransactionTypeRequestDTO {
    private long id;
    private String description;

    public static TransactionType newTransactionType(TransactionTypeRequestDTO transactionType) {
        return TransactionType.builder()
                .id(transactionType.getId())
                .description(transactionType.getDescription())
                .build();
    }
}
