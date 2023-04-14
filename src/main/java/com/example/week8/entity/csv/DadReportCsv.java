package com.example.week8.entity.csv;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter@Setter
@NoArgsConstructor
public class DadReportCsv {

    private String requestDate; //기준 날짜
    private Long dadDetId; //직접광고 상세 ID
    private Long showCount; //노출 수
    private Long clickCount; //클릭 수
    private Double avgShowRank; //평균 노출 순위
    private Double avgCpc; //평균 CPC

    @NotBlank
    @NotNull
    private Long adCost; //광고비

    @Builder
    public DadReportCsv(String requestDate, Long dadDetId, Long showCount, Long clickCount, Double avgShowRank, Double avgCpc, Long adCost) {
        this.requestDate = requestDate;
        this.dadDetId = dadDetId;
        this.showCount = showCount;
        this.clickCount = clickCount;
        this.avgShowRank = avgShowRank;
        this.avgCpc = avgCpc;
        this.adCost = adCost;
    }

    //널 체크
    public void checkNull(){
        if(requestDate == null){
            throw new NullPointerException("null입니다용..");
        }else if(dadDetId == null){
            throw new NullPointerException("null입니다용..");
        }else if(showCount == null){
            throw new NullPointerException("null입니다용..");
        }else if(requestDate == null){
            throw new NullPointerException("null입니다용..");
        }else if(clickCount == null){
            throw new NullPointerException("null입니다용..");
        }else if(avgShowRank == null){
            throw new NullPointerException("null입니다용..");
        }else if(avgCpc == null){
            throw new NullPointerException("null입니다용..");
        }else if(adCost == null){
            throw new NullPointerException("null입니다용..");
        }

    }


}
