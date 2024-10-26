package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    
}
