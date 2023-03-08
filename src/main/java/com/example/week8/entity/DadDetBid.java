package com.example.week8.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "DAD_DET_BID")
public class DadDetBid {

    @Id
//    @Column(name = "sdssd")
    private Long id; //직접광고 상세 ID(FK)

    @OneToOne
    @MapsId
    @JoinColumn(name = "DAD_DET_BID_ID", referencedColumnName = "DAD_DET_ID")
    private DadDet dadDet;

    private Long bidCost; //입찰 금액

}
