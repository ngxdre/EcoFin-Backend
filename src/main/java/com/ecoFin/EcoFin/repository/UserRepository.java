package com.ecoFin.EcoFin.repository;

import com.ecoFin.EcoFin.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
