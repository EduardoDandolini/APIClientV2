package com.teste.projetoAPI.service;

import com.teste.projetoAPI.entities.Category;
import com.teste.projetoAPI.entities.Order;
import com.teste.projetoAPI.repository.CategoryRepository;
import com.teste.projetoAPI.service.exceptions.DataBaseException;
import com.teste.projetoAPI.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById (Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.get();
    }

    public Category insert (Category category) {
        return categoryRepository.save(category);
    }

    public void delete (Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Category update (Long id,Category category) {
        try {
            Category entity = categoryRepository.getReferenceById(id);
            updateData(entity, category);
            return categoryRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Category entity, Category category) {
        entity.setName(category.getName());
    }
}
