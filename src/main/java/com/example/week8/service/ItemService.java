package com.example.week8.service;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.item.find.ItemFindResDto;
import com.example.week8.dto.item.save.ItemSaveReqDto;
import com.example.week8.dto.item.save.ItemSaveResDto;
import com.example.week8.entity.Item;
import com.example.week8.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //조회
    public ItemFindResDto itemFind(String itmename, String itmenumber){
        ItemFindResDto itemFindResDto =  ItemFindResDto.builder()
                .items(itemRepository.findByItemNoContainingAndItemNameContaining(itmenumber,itmename))
                .build();
        return itemFindResDto;
    }
    
    //저장
    public ItemSaveResDto itemSave(ItemSaveReqDto itemSaveReqDto){
        Item item = itemRepository.save(itemSaveReqDto.toItem());
        return item.toItemSaveResDto();
    }

}