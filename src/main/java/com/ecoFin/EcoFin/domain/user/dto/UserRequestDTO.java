package com.ecoFin.EcoFin.domain.user.dto;

import com.ecoFin.EcoFin.domain.user.entity.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class UserRequestDTO {
    private Long id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    private BigDecimal salary;

    public static User newUser(UserRequestDTO user) {
        return User.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .password(user.getPassword())
                .salary(user.getSalary())
                .build();
    }
}
