package com.example.pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.dto.CategoryDto;
import com.example.pos.dto.ItemDto;
import com.example.pos.entity.Category;
import com.example.pos.entity.Item;
import com.example.pos.service.CategoryService;
import com.example.pos.service.ItemService;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setQty(itemDto.getQty());
        item.setUnitPrice(itemDto.getUnitPrice());
    
        CategoryDto categoryDto = categoryService.getByIdCategory(itemDto.getCategory().getCategoryId());
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        item.setCategory(category);

        return ResponseEntity.status(201).body(itemService.createItem(item));
    }

    @GetMapping("")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.status(200).body(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ItemDto getByIdItem(@PathVariable Long id) {
        return itemService.getByIdItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setQty(itemDto.getQty());
        item.setUnitPrice(itemDto.getUnitPrice());
    
        CategoryDto categoryDto = categoryService.getByIdCategory(itemDto.getCategory().getCategoryId());
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        item.setCategory(category);
        
        return ResponseEntity.status(201).body(itemService.updateItem(id, item));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
