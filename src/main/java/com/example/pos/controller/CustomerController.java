package com.example.pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.entity.Customer;
import com.example.pos.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/v1/customer")
//@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    @PreAuthorize("hasRole('USER','ADMIN')")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        if (customer.getCustname()==null || customer.getCustname() == "") {
            return ResponseEntity.status(422).body("Please enter your name");
        }

        if (customer.getAddress() == null || customer.getAddress() == "") {
            return ResponseEntity.status(422).body("Please enter your address");
        }

        if (customer.getCity() ==null || customer.getCity() == "") {
            return ResponseEntity.status(422).body("Please enter your city");
        }

        if (customer.getPhoneNo() ==null || customer.getPhoneNo() == "") {
            return ResponseEntity.status(422).body("Please enter your phone no");
        }

        customerService.createCustomer(customer);

        return ResponseEntity.status(201).body("Added Successfully");
    }
    
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> getByIdCustomers(@PathVariable Long id) {
        Customer existCustomer = customerService.getByIdCustomer(id);

        if (existCustomer == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(existCustomer);
        }
        
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedCustomer);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
    
}
