package com.example.week8.entity;

import com.example.week8.dto.item.save.ItemSaveResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private Long id; //상품 아이디

    private String itemNo;//상품 번호

    private String itemName;//상품 명

    private Integer adultYn;//성인 여부 default:true(1)

    private Long itemOrgCost;//상품 원본 금액

    @Column(nullable = false)
    private Integer itemActYn;//상품 활성 여부

    @Builder
    public Item(Long id, String itemNo, String itemName, Integer adultYn, Long itemOrgCost, Integer itemActYn) {
        this.id = id;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.adultYn = adultYn;
        this.itemOrgCost = itemOrgCost;
        this.itemActYn = itemActYn;
    }

    public ItemSaveResDto toItemSaveResDto(){
        return ItemSaveResDto.builder()
                .itemNo(itemNo)
                .itemName(itemName)
                .adultYn(adultYn)
                .itemOrgCost(itemOrgCost)
                .itemActYn(itemActYn)
                .build();
    }
}
