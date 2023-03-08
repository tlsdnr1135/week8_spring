package com.example.week8.service;

import com.example.week8.entity.Item;
import com.example.week8.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item itemSave(Item item){
        Item itemEntity = itemRepository.save(item);
        return itemEntity;
    }

}
