package com.example.pos.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    /*@Override
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
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByUsername(username).orElse(null);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Convert roles from List<String> to a List of GrantedAuthority
        List<GrantedAuthority> authorities = customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                customer.getUsername(),
                customer.getPassword(),
                authorities
        );
    }
}
