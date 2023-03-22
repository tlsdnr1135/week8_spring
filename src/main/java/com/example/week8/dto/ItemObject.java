package com.example.week8.dto;

import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.example.week8.dto.item.find.ItemListFindResDto;
import com.example.week8.entity.Agroup;
import com.example.week8.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemObject {

    private List<Item> items;

    private List<Agroup> agroups;

    private List<AgroupFindResDto> agroupFindResDtos;

    private ItemListFindResDto itemFindResDto;

}
