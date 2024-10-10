package com.ecoFin.EcoFin.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "usuario")
@Entity(name = "usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String email;
    private String senha;
    private BigDecimal salario;
}
