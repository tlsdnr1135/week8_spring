package com.example.week8.controller;

import com.example.week8.dto.item.find.ItemListFindJoinAdWhereItemNoAndItemNameResDto;
import com.example.week8.dto.item.find.ItemListFindResDto;
import com.example.week8.dto.item.save.ItemSaveReqDto;
import com.example.week8.dto.item.save.ItemSaveResDto;
import com.example.week8.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/item/find")
    public ResponseEntity<List<ItemListFindResDto>> findItems(@RequestParam(required = false) String itemname, String itemnumber){
        return ResponseEntity.ok().body(itemService.itemFind(itemname,itemnumber));
    }
    @GetMapping("/api/item/find/joinad")
    public ResponseEntity<List<ItemListFindJoinAdWhereItemNoAndItemNameResDto>> findItemListJoinAdWhereItemNameAndItemNo(@RequestParam(required = false) String itemName, String itemNo, String advId, Long agroupId){
        System.out.println(itemName);
        System.out.println(itemNo);
        System.out.println(advId);
        System.out.println(agroupId);
        if(StringUtils.isEmpty(itemName)){
            itemName ="";
        }
        if(itemNo == null){
            itemNo ="";
        }
        return ResponseEntity.ok().body(itemService.findItemListJoinAdWhereItemNameAndItemNo(advId,agroupId,itemNo,itemName));
    }

    @PostMapping("/api/item/save")
    public ResponseEntity<ItemSaveResDto> saveItems(@RequestBody ItemSaveReqDto itemSaveReqDto){
        return ResponseEntity.ok().body(itemService.itemSave(itemSaveReqDto));
    }

}
