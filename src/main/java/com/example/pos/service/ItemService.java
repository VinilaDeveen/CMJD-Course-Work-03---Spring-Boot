package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.dto.ItemDto;
import com.example.pos.entity.Item;

@Service
public interface ItemService {
    Item createItem(Item item);
    List<ItemDto> getAllItems();
    ItemDto getByIdItem(Long id);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
}