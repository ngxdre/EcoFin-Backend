package com.ecoFin.EcoFin.domain.transaction.dto;

import com.ecoFin.EcoFin.domain.category.entity.Category;
import com.ecoFin.EcoFin.domain.transaction.entity.Transaction;
import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransactionResponseDTO {
    private long id;
    private String title;
    private String description;
    private double value;
    private LocalDate date;
    private Category category;
    private TransactionType transactionType;
    private User user;

    public static TransactionResponseDTO fromTransaction(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .title(transaction.getTitle())
                .description(transaction.getDescription())
                .value(transaction.getValue())
                .date(transaction.getDate())
                .category(transaction.getCategory())
                .transactionType(transaction.getTransactionType())
                .user(transaction.getUser())
                .build();
    }
}
