package com.example.week8.dto.dadreport.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDadReportListDto {


    private String key;
    private String date;
    private Long dadDetId;
    private Long showCount; //노출 수
    private Long clickCount; //클릭 수
    private Double avgShowRank; //평균 노출 순위
    private Double avgCpc; //평균 클릭 수
    private Long adCost; //광고비


    @Builder
    public ResponseDadReportListDto(String key, String date, Long dadDetId, Long showCount, Long clickCount, Double avgShowRank, Double avgCpc, Long adCost) {
        this.key = key;
        this.date = date;
        this.dadDetId = dadDetId;
        this.showCount = showCount;
        this.clickCount = clickCount;
        this.avgShowRank = avgShowRank;
        this.avgCpc = avgCpc;
        this.adCost = adCost;
    }
}
