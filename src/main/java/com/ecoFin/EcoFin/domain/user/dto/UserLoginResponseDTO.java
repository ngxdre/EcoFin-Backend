package com.ecoFin.EcoFin.domain.user.dto;

import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserLoginResponseDTO {
    private long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private double balance;
    private String token;

    public static UserLoginResponseDTO fromUser(User user, String token) {
        return UserLoginResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .balance(user.getBalance())
                .token(token)
                .build();
    }
}
