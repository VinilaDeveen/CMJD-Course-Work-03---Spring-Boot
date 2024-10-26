package com.example.pos.dto;

import java.util.List;

import com.example.pos.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {
    private Long saleId;
    private List<ItemDatail> itemDatails;
    private Customer customer;

    @Data
    public static class ItemDatail {
        private Long itemId;
        private String name;
        private Integer qty;
        private Double unitPrice;
    }
}
