package com.ecoFin.EcoFin.domain.category.dto;

import com.ecoFin.EcoFin.domain.category.entity.Category;
import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategoryResponseDTO {
    private long id;
    private String description;
    private double budget;
    private User user;

    public static CategoryResponseDTO fromCategory(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .description(category.getDescription())
                .budget(category.getBudget())
                .user(category.getUser())
                .build();
    }
}
