package com.ecoFin.EcoFin.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "users")
@Entity(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome deve ser preenchido")
    private String name;
    @NotBlank(message = "Úlitimo nome deve ser preenchido")
    private String lastName;
    @NotNull(message = "Data de nascimento deve ser preenchida")
    @Past
    private LocalDate birthDate;
    @Email
    @NotBlank(message = "E-mail deve ser preenchido")
    private String email;
    @NotBlank(message = "Senha deve ser preenchida")
    private String password;
    @NotNull(message = "Salário deve ser preenchido")
    @Min(0)
    private BigDecimal salary;
}
