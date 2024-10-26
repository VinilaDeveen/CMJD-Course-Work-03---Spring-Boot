package com.example.pos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.dto.CategoryDto;
import com.example.pos.entity.Category;
import com.example.pos.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        
        return ResponseEntity.status(201).body(categoryService.createCategory(category));
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    

    @GetMapping("/{id}")
    public CategoryDto getCategoryWithItems(@PathVariable Long id) {
        return categoryService.getByIdCategory(id);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(category.getCategoryName());
        Category updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory == null) {
           return ResponseEntity.status(404).body(null); 
        } else {
            return ResponseEntity.status(200).body(updatedCategory);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
