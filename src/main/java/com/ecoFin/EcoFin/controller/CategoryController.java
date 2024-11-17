package com.ecoFin.EcoFin.controller;

import com.ecoFin.EcoFin.domain.category.dto.CategoryRequestDTO;
import com.ecoFin.EcoFin.domain.category.dto.CategoryResponseDTO;
import com.ecoFin.EcoFin.domain.category.entity.Category;

import com.ecoFin.EcoFin.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> listAll() {
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getById(@PathVariable("categoryId") long categoryId) {
        Optional<Category> category = service.getById(categoryId);
        return category.isPresent() ? ResponseEntity.ok().body(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryRequestDTO data) {
        service.save(CategoryRequestDTO.newCategory(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateById(
            @PathVariable("categoryId") long categoryId,
            @Valid @RequestBody CategoryRequestDTO data
    ) {
        Optional<Category> category = service.updateById(categoryId, data);
        return category.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable("categoryId") long categoryId) {
        boolean deleted = service.deleteById(categoryId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
