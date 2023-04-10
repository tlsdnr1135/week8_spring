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
    private Long showCount; //���� ��
    private Long clickCount; //Ŭ�� ��
    private Double avgShowRank; //��� ���� ����
    private Double avgCpc; //��� Ŭ�� ��
    private Long adCost; //�����


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
