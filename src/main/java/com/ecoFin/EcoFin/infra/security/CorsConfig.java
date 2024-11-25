package com.ecoFin.EcoFin.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000"); // Origem permitida
        config.addAllowedMethod("*"); // Permite todos os métodos (GET, POST, PUT, DELETE, etc.)
        config.addAllowedHeader("*"); // Permite todos os cabeçalhos
        config.setAllowCredentials(true); // Permite cookies ou tokens

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica para todas as rotas
        return new CorsFilter(source);
    }
}

