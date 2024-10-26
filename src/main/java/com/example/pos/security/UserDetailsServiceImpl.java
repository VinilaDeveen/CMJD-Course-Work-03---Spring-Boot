package com.example.pos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pos.entity.Customer;
import com.example.pos.repository.CustomerRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerRepo.findByUsername(username).orElse(null);

        if(customer == null) {
            throw new UsernameNotFoundException("User is not found");
        } else {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(customer.getUsername())
                    .password(customer.getPassword())
                    .build();
        }
    }
}
