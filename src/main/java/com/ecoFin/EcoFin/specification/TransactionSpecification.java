package com.ecoFin.EcoFin.specification;

import com.ecoFin.EcoFin.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class TransactionSpecification {
    public static Specification<Transaction> userIs(long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Transaction> categoryIs(long categoryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Transaction> transactionTypeIs(long transactionTypeId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transactionType").get("id"), transactionTypeId);
    }

    public static Specification<Transaction> dateBetween(LocalDate initialDate, LocalDate endingDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("date"), initialDate, endingDate);
    }
}