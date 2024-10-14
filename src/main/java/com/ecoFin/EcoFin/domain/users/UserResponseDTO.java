package com.ecoFin.EcoFin.domain.users;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    private BigDecimal salary;

    public static UserResponseDTO fromUser(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .password(user.getPassword())
                .salary(user.getSalary())
                .build();
    }
}
