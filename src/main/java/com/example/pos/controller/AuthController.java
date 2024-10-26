package com.example.pos.controller;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.entity.Customer;
import com.example.pos.repository.CustomerRepo;
import com.example.pos.security.JwtUtils;
import com.example.pos.service.CustomerService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("auth/login")
    public String login (@RequestBody Customer customer) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        return jwtToken;
    }

    @PostMapping("auth/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        customer.setRoles(Collections.singletonList("ADMIN"));

        customerService.createCustomer(customer);

        return ResponseEntity.ok("User registered successfully!");
    }
}
