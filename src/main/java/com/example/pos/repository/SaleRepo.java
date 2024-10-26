package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Sale;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long>{
    
}
