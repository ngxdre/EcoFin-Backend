package com.ecoFin.EcoFin.domain.user.dto;

import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class UserRequestDTO {
    private long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private double balance;

    public static User newUser(UserRequestDTO user) {
        return User.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .password(user.getPassword())
                .balance(user.getBalance())
                .build();
    }
}
