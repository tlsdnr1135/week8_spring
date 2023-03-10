package com.example.week8.service;

import com.example.week8.dto.ItemObject;
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
    //TODO 쿼리 DSL로 바꾸기
    public ItemObject itemFind(String itmename, String itmenumber){
        ItemObject itemObject = new ItemObject();
//        if(itmename==null && itmenumber==null){ //둘다 널 -> 전체 조회
//
//            itemObject.setItems(itemRepository.findAll());
//            return itemObject;
//        }else if(itmename == (null)){ //이름 없음 -> 넘버만 조건
//            return null;
//        }else if(itmenumber == (null)){ //넘버 없음 -> 이름만 조건
//            return null;
//        }else { //둘 다 조건
//            itemObject.setItems(itemRepository.findByItemNoContainingAndItemNameContaining(itmenumber,itmename));
//            return itemObject;
//        }
        itemObject.setItems(itemRepository.findByItemNoContainingAndItemNameContaining(itmenumber,itmename));
        return itemObject;
    }
    
    //저장
    public Item itemSave(Item item){
        Item itemEntity = itemRepository.save(item);
        return itemEntity;
    }

}