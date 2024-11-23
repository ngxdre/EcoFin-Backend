package com.ecoFin.EcoFin.domain.user.dto;

import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class UserResponseDTO {
    private long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private double balance;

    public static UserResponseDTO fromUser(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .password(user.getPassword())
                .balance(user.getBalance())
                .build();
    }
}
