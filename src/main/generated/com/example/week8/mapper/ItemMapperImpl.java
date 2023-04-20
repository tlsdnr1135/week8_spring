package com.example.week8.mapper;

import com.example.week8.dto.item.find.ItemListFindResDto;
import com.example.week8.dto.item.find.ItemListFindResDto.ItemListFindResDtoBuilder;
import com.example.week8.entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T09:29:39+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
public class ItemMapperImpl implements ItemMapper {

    @Override
    public List<ItemListFindResDto> toItemFindResDto(List<Item> itemList) {
        if ( itemList == null ) {
            return null;
        }

        List<ItemListFindResDto> list = new ArrayList<ItemListFindResDto>( itemList.size() );
        for ( Item item : itemList ) {
            list.add( toItemListFindResDto( item ) );
        }

        return list;
    }

    @Override
    public ItemListFindResDto toItemListFindResDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemListFindResDtoBuilder itemListFindResDto = ItemListFindResDto.builder();

        itemListFindResDto.key( item.getId() );
        itemListFindResDto.adultYn( item.getAdultYn() );
        itemListFindResDto.itemActYn( item.getItemActYn() );
        itemListFindResDto.itemName( item.getItemName() );
        itemListFindResDto.itemNo( item.getItemNo() );
        itemListFindResDto.itemOrgCost( item.getItemOrgCost() );

        return itemListFindResDto.build();
    }
}
