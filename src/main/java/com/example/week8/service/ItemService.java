package com.example.week8.service;

import com.example.week8.dto.item.find.ItemListFindJoinAdWhereItemNoAndItemNameResDto;
import com.example.week8.dto.item.find.ItemListFindResDto;
import com.example.week8.dto.item.save.ItemSaveReqDto;
import com.example.week8.dto.item.save.ItemSaveResDto;
import com.example.week8.entity.Item;
import com.example.week8.mapper.ItemMapper;
import com.example.week8.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //조회
    public List<ItemListFindResDto> itemFind(String itmename, String itmenumber){
        List<Item> itemList = itemRepository.findByItemNoContainingAndItemNameContaining(itmenumber, itmename);
        List<ItemListFindResDto> itemListFindResDtos = ItemMapper.INSTANCE.toItemFindResDto(itemList);
        return itemListFindResDtos;
    }
    //조회
    public List<ItemListFindJoinAdWhereItemNoAndItemNameResDto> findItemListJoinAdWhereItemNameAndItemNo(String advId, Long agroupId, String itemNo, String itemName  ){
        List<ItemListFindJoinAdWhereItemNoAndItemNameResDto> itemListJoinAdWhereItemNameAndItemNo = itemRepository.findItemListJoinAdWhereItemNameAndItemNo(advId, agroupId, itemNo, itemName);
        return itemListJoinAdWhereItemNameAndItemNo;
    }
    
    //저장
    public ItemSaveResDto itemSave(ItemSaveReqDto itemSaveReqDto){
        System.out.println("나는 아이템 저장");
        Item item = itemRepository.save(itemSaveReqDto.toItem());
        return item.toItemSaveResDto();
    }

}