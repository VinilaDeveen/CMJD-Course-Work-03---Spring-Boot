package com.example.pos.dto;

import java.math.BigDecimal;

import com.example.pos.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long itemId;
    private String name;
    private Integer qty;
    private BigDecimal unitPrice;
    private Category category;
}
