package com.ecoFin.EcoFin.domain.transactionType.dto;

import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionTypeResponseDTO {
    private long id;
    private String description;

    public static TransactionTypeResponseDTO fromTransactionType(TransactionType transactionType) {
        return TransactionTypeResponseDTO.builder()
                .id(transactionType.getId())
                .description(transactionType.getDescription())
                .build();
    }
}
