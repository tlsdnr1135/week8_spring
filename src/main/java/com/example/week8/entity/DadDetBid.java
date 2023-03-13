package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "DAD_DET_BID")
public class DadDetBid {

    @Id
//    @Column(name = "DAD_DET_BID_ID")
    private Long id; //직접광고 상세 ID(FK)

    @OneToOne
    @MapsId
    @JoinColumn(name = "DAD_DET_BID_ID", referencedColumnName = "DAD_DET_ID")
    private DadDet dadDet;

    private Long bidCost; //입찰 금액

    @Builder
    public DadDetBid(Long id, DadDet dadDet, Long bidCost) {
        this.id = id;
        this.dadDet = dadDet;
        this.bidCost = bidCost;
    }
}
