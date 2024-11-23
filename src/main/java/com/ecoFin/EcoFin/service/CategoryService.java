package com.ecoFin.EcoFin.service;

import com.ecoFin.EcoFin.domain.category.dto.CategoryRequestDTO;
import com.ecoFin.EcoFin.domain.category.dto.CategoryResponseDTO;
import com.ecoFin.EcoFin.domain.category.entity.Category;
import com.ecoFin.EcoFin.repository.CategoryRepository;

import com.ecoFin.EcoFin.specification.CategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<CategoryResponseDTO> listByUser(long userId) {
        return repository.findAll(Specification
                    .where(CategorySpecification.userIs(userId))
                )
                .stream()
                .map(CategoryResponseDTO::fromCategory)
                .collect(Collectors.toList());
    }

    public Optional<Category> getById(long id) {
        if (!repository.existsById(id)) return Optional.empty();
        return repository.findById(id);
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Optional<Category> updateById(long id, CategoryRequestDTO request) {
        if (!repository.existsById(id)) return Optional.empty();

        Category category = CategoryRequestDTO.newCategory(request);
        category.setId(id);

        return Optional.of(repository.save(category));
    }

    public boolean deleteById(long id) {
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
