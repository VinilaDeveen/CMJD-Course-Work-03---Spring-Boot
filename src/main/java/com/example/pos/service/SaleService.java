package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.dto.SaleDto;
import com.example.pos.entity.Sale;

@Service
public interface SaleService {
    String creatSale(SaleDto saleDto);
    List<Sale> getAllSales();
    Sale getSaleDetailById(Long saleId);
    void deleteSale(Long saleId);
}
