package com.example.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pos.entity.Customer;
import com.example.pos.repository.CustomerRepo;
import com.example.pos.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getByIdCustomer(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepo.findById(id).orElse(null);

        if (existingCustomer != null) {
            existingCustomer.setCustname(customer.getCustname());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setPhoneNo(customer.getPhoneNo());
            return customerRepo.save(existingCustomer);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }
    
}
