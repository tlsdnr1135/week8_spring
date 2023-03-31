package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@IdClass(DadReportPK.class)
@NoArgsConstructor
public class DadReport {

    @Id
    private String advId; //광고주 ID
    @Id
    private String requestDate; //기준 날짜
    @Id
    private Long dadDetId; //직접광고 상세 ID

    private Long showCount; //노출 수
    private Long clickCount; //클릭 수
    private Long avgShowRank; //평균 노출 순위
    private Long avgCpc; //평균 CPC
    private Long adCost; //광고비

    @Builder
    public DadReport(String advId, String requestDate, Long dadDetId, Long showCount, Long clickCount, Long avgShowRank, Long avgCpc, Long adCost) {
        this.advId = advId;
        this.requestDate = requestDate;
        this.dadDetId = dadDetId;
        this.showCount = showCount;
        this.clickCount = clickCount;
        this.avgShowRank = avgShowRank;
        this.avgCpc = avgCpc;
        this.adCost = adCost;
    }
}
