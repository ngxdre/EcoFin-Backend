package com.ecoFin.EcoFin.repository;

import com.ecoFin.EcoFin.domain.transactionType.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> { }
