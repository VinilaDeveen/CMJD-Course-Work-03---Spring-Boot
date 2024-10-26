package com.example.pos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.dto.ItemDto;
import com.example.pos.entity.Item;
import com.example.pos.repository.ItemRepo;
import com.example.pos.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public Item createItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepo.findAll()
        .stream()
        .map(item -> new ItemDto(item.getItemId(), item.getName(), item.getQty(), item.getUnitPrice(), item.getCategory()))
        .collect(Collectors.toList());
    }

    @Override
    public ItemDto getByIdItem(Long id) {
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return new ItemDto(item.getItemId(),item.getName(), item.getQty(), item.getUnitPrice(),item.getCategory());
    }

    @Override
    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepo.findById(id).orElse(null);

        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setQty(item.getQty());
            existingItem.setUnitPrice(item.getUnitPrice());
            existingItem.setCategory(item.getCategory());
            return itemRepo.save(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }

}
