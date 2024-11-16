package com.ecoFin.EcoFin.domain.category.entity;

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

    @NotBlank(message = "Descrição deve ser preenchida")
    private String description;

    @NotNull(message = "Orçamento deve ser preenchido")
    @Min(0)
    private BigDecimal budget;
}
