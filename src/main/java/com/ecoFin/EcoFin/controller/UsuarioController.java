package com.ecoFin.EcoFin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @GetMapping
    public String HelloWorld(){ //teste para verificar se está rodando
        return "Hello World";
    }
}
