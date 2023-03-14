package com.example.week8.dto.item.save;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveResDto {

    private String itemNo;
    private String itemName;
    private Integer adultYn;
    private Long itemOrgCost;
    private Integer itemActYn;

    @Builder
    public ItemSaveResDto(String itemNo, String itemName, Integer adultYn, Long itemOrgCost, Integer itemActYn) {
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.adultYn = adultYn;
        this.itemOrgCost = itemOrgCost;
        this.itemActYn = itemActYn;
    }
}
