package com.example.pos.dto;

import java.util.List;

import com.example.pos.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private List<Item> items;
}
