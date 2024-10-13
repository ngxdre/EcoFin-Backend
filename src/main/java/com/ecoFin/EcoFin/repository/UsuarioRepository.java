package com.ecoFin.EcoFin.repository;

import com.ecoFin.EcoFin.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
