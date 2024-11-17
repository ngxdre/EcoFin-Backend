package com.ecoFin.EcoFin.domain.transaction.entity;

import com.ecoFin.EcoFin.domain.category.entity.Category;
import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import com.ecoFin.EcoFin.domain.user.entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "transactions")
@Entity(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "A transaction must have a title")
    private String title;

    @NotBlank(message = "A transaction must have a description")
    private String description;

    @NotNull(message = "A transaction must have a value")
    @Min(0)
    private double value;

    @NotNull(message = "A transaction must have a date")
    private LocalDate date;

    @NotNull(message = "A transaction must have a category")
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @NotNull(message = "A transaction must have a transaction type")
    @ManyToOne
    @JoinColumn(name = "fk_transactionType")
    private TransactionType transactionType;

    @NotNull(message = "A transaction must have a user")
    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
}
