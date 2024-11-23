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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryResponseDTO>> listAll(@PathVariable("userId") long userId) {
        return ResponseEntity.ok().body(service.listByUser(userId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getById(@PathVariable("categoryId") long id) {
        Optional<Category> category = service.getById(id);
        return category.isPresent() ? ResponseEntity.ok().body(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryRequestDTO data) {
        service.save(CategoryRequestDTO.newCategory(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateById(
            @PathVariable("categoryId") long id,
            @Valid @RequestBody CategoryRequestDTO data
    ) {
        Optional<Category> category = service.updateById(id, data);
        return category.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable("categoryId") long id) {
        boolean deleted = service.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
