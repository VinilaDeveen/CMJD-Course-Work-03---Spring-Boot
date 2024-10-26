package com.example.pos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    
}
