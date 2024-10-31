package com.ecoFin.EcoFin.domain.user.dto;

import java.math.BigDecimal;
import java.util.Date;

public record RegisterRequestDTO (String name, String lastName,Date birthDate,
                                  String email, String password, BigDecimal salary){
}
