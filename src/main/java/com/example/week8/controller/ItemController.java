package com.example.week8.controller;

import com.example.week8.dto.ItemObject;
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
    public ResponseEntity<ItemObject> findItems(@RequestParam(required = false) String itemname, String itemnumber, HttpServletRequest request){
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        System.out.println(itemname);
        System.out.println(itemnumber);

        return ResponseEntity.ok().body(itemService.itemFind(itemname,itemnumber));
    }
    @PostMapping("/api/item/save")
    public ResponseEntity<?> saveItems(@RequestBody Item item){
        itemService.itemSave(item);
        return null;
    }

}
