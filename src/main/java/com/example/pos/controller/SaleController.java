package com.example.pos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.dto.SaleDto;
import com.example.pos.entity.Sale;
import com.example.pos.service.SaleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/sale")
//@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("")
    @PreAuthorize("hasRole('USER','ADMIN')") // Allow only users with the USER role
    public ResponseEntity<String> createSale(@RequestBody SaleDto saleDto) {
        try {
            String response = saleService.creatSale(saleDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{saleId}")
    
    @PreAuthorize("hasRole('USER','ADMIN')") // Allow only users with the USER role
    public ResponseEntity<Sale> getSaleById(@PathVariable Long saleId) {
        try {
            Sale sale = saleService.getSaleDetailById(saleId);
            return ResponseEntity.ok(sale);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER','ADMIN')") // Allow only users with the USER role
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.status(200).body(saleService.getAllSales());
    }

    @DeleteMapping("/{saleId}")
    @PreAuthorize("hasRole('ADMIN')") // Allow only admins
    public void deleteCustomer(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
    }
}

