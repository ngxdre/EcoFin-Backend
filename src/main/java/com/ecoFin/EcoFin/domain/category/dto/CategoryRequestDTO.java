package com.ecoFin.EcoFin.domain.category.dto;

import com.ecoFin.EcoFin.domain.category.entity.Category;
import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoryRequestDTO {
    private long id;
    private String description;
    private double budget;
    private User user;

    public static Category newCategory(CategoryRequestDTO category) {
        return Category.builder()
                .id(category.getId())
                .description(category.getDescription())
                .budget(category.getBudget())
                .user(category.getUser())
                .build();
    }
}
