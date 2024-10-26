package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.dto.CategoryDto;
import com.example.pos.entity.Category;

@Service
public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    CategoryDto getByIdCategory(Long id);
}
