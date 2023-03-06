package com.example.week8.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "DAD_DET_BID")
public class DadDetBid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DAD_DET_ID")
    private Long id; //직접광고 상세 ID(FK)

    private Long bidCost; //입찰 금액

}
