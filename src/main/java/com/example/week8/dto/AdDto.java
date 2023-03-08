package com.example.week8.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdDto {

    private Long agroupId; //광고 그룹 ID
    private String agroupName; //광고 그룹 명

    //------------------------------------------------------------------------
    private Long itemId; //아이템 ID
    private String itemNo;//상품 번호
    private String itemName;//상품 명
    private Long itemOrgCost;//상품 원본 금액
    //------------------------------------------------------------------------
    private String advId; //광고주 ID



}
