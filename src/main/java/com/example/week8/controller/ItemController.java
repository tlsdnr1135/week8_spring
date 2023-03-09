package com.example.week8.controller;

import com.example.week8.dto.ItemObject;
import com.example.week8.entity.Item;
import com.example.week8.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/item/find")
    public ResponseEntity<ItemObject> findItems(@RequestParam(required = false) String itmename, String itmenumber){
        System.out.println(itmename);
        System.out.println(itmenumber);

        return ResponseEntity.ok().body(itemService.itemFind(itmename,itmenumber));
    }
    @PostMapping("/api/item/save")
    public ResponseEntity<?> saveItems(@RequestBody Item item){
        itemService.itemSave(item);
        return null;
    }

}
