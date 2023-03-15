package com.example.week8.controller;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.item.find.ItemFindResDto;
import com.example.week8.dto.item.save.ItemSaveReqDto;
import com.example.week8.dto.item.save.ItemSaveResDto;
import com.example.week8.entity.Item;
import com.example.week8.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/item/find")
    public ResponseEntity<ItemFindResDto> findItems(@RequestParam(required = false) String itemname, String itemnumber){
        return ResponseEntity.ok().body(itemService.itemFind(itemname,itemnumber));
    }
    @PostMapping("/api/item/save")
    public ResponseEntity<ItemSaveResDto> saveItems(@RequestBody ItemSaveReqDto itemSaveReqDto){
        return ResponseEntity.ok().body(itemService.itemSave(itemSaveReqDto));
    }

}
