package com.example.week8.dto.item.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemListFindResDto {

    private Long key;
    private Integer adultYn;
    private Integer itemActYn;
    private String itemName;
    private String itemNo;
    private Long itemOrgCost;

    @Builder
    public ItemListFindResDto(Long key, Integer adultYn, Integer itemActYn, String itemName, String itemNo, Long itemOrgCost) {
        this.key = key;
        this.adultYn = adultYn;
        this.itemActYn = itemActYn;
        this.itemName = itemName;
        this.itemNo = itemNo;
        this.itemOrgCost = itemOrgCost;
    }
}

