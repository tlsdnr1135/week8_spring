package com.example.week8.controller;

import com.example.week8.entity.Item;
import com.example.week8.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/item/save")
    public ResponseEntity<?> saveItems(@RequestBody Item item){
        itemService.itemSave(item);
        return null;
    }

}
