package com.example.week8.dto;

import com.example.week8.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AdDto {

    private Adv adv;
    private Agroup agroup;
    private Item item;
    private List<KwdDto> kwd;

    @Builder
    public AdDto(Adv adv, Agroup agroup, Item item, List<KwdDto> kwd) {
        this.adv = adv;
        this.agroup = agroup;
        this.item = item;
        this.kwd = kwd;
    }
    //    private CnrReq cnrReq;



//    private Long agroupId; //광고 그룹 ID
//    private String agroupName; //광고 그룹 명

    //------------------------------------------------------------------------
//    private Long itemId; //아이템 ID
//    private String itemNo;//상품 번호
//    private String itemName;//상품 명
//    private Long itemOrgCost;//상품 원본 금액
    //------------------------------------------------------------------------
//    private String advId; //광고주 ID



}
