package com.ecoFin.EcoFin.service;

import com.ecoFin.EcoFin.domain.category.dto.CategoryRequestDTO;
import com.ecoFin.EcoFin.domain.category.dto.CategoryResponseDTO;
import com.ecoFin.EcoFin.domain.category.entity.Category;
import com.ecoFin.EcoFin.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<CategoryResponseDTO> listAllCategories() {
        return repository.findAll()
                .stream()
                .map(CategoryResponseDTO::fromCategory)
                .collect(Collectors.toList());
    }

    public Optional<Category> getCategoryById(long id) {
        if (!repository.existsById(id)) return Optional.empty();
        return repository.findById(id);
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public Optional<Category> updateCategoryById(long id, CategoryRequestDTO request) {
        if (!repository.existsById(id)) return Optional.empty();

        Category category = CategoryRequestDTO.newCategory(request);
        category.setId(id);

        return Optional.of(repository.save(category));
    }

    public boolean deleteCategoryById(long id) {
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}