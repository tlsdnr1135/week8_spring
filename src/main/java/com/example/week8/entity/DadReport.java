package com.example.week8.entity;

import com.example.week8.entity.csv.DadReportCsv;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@IdClass(DadReportPK.class)
@NoArgsConstructor
@Table(name = "DAD_DET_REPORT")
public class DadReport {

    @Id
    private String advId; //광고주 ID
    @Id
    @Column(name = "BASE_DATE")
    private String requestDate; //기준 날짜
    @Id
    private Long dadDetId; //직접광고 상세 ID

    @Column(name = "IMPRESSIONS")
    private Long showCount; //노출 수

    @Column(name = "CLICKS")
    private Long clickCount; //클릭 수

    @Column(name = "AVERAGE_IMPRESSION_RANK")
    private Double avgShowRank; //평균 노출 순위

    @Column(name = "AVERAGE_CLICK_COST")
    private Double avgCpc; //평균 CPC

    @Column(name = "ADVERTISING_COST")
    private Long adCost; //광고비

    @Builder
    public DadReport(String advId, String requestDate, Long dadDetId, Long showCount, Long clickCount, Double avgShowRank, Double avgCpc, Long adCost) {
        this.advId = advId;
        this.requestDate = requestDate;
        this.dadDetId = dadDetId;
        this.showCount = showCount;
        this.clickCount = clickCount;
        this.avgShowRank = avgShowRank;
        this.avgCpc = avgCpc;
        this.adCost = adCost;
    }

    //중복일 경우
    public DadReport isDadReportDuplication(DadReportCsv item){
        this.showCount += item.getShowCount();
        this.clickCount += item.getClickCount();
        this.adCost += item.getAdCost();
        this.avgShowRank = (double) Math.round( ((this.avgShowRank + item.getAvgShowRank())/2)*10 )/10.0;
        this.avgCpc = (double) Math.round( ((this.avgCpc + item.getAvgCpc())/2)*10 )/10.0;
        return this;
    }

}
