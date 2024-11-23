package com.ecoFin.EcoFin.domain.category.entity;

import com.ecoFin.EcoFin.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "categories")
@Entity(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "A category must have a description")
    private String description;

    @NotNull(message = "A category must have a budget")
    @Min(0)
    private double budget;

    @NotNull(message = "A category must have a user")
    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
}
