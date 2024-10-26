package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.entity.Customer;

@Service
public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getByIdCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}
