package com.example.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.repository.CategoryRepo;
import com.example.pos.service.CategoryService;
import com.example.pos.dto.CategoryDto;
import com.example.pos.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepo.findById(id).orElse(null);

        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepo.save(existingCategory);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryDto getByIdCategory(Long Id) {
        Category category = categoryRepo.findById(Id)
                                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryDto(category.getCategoryId(), category.getCategoryName());
    }
  
}
