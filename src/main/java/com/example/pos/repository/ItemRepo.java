package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    
}
