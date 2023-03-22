package com.example.week8.mapper;

import com.example.week8.dto.item.find.ItemListFindResDto;
import com.example.week8.entity.Item;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    //Item -> ItemFindResDto
    @IterableMapping(qualifiedByName = "v2")
    List<ItemListFindResDto> toItemFindResDto(List<Item> itemList);

    @Named("v2")
    @Mapping(target = "key",source  = "id")
    ItemListFindResDto toItemListFindResDto(Item item);

}
