package com.example.week8.entity;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "KWD")
public class Kwd { //키워드

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KWD_ID")
    private Long id; //키워드 ID

    private String kwdName; //키워드 명
    private Integer sellPossKwdYn; //판매 가능 키워드 여부
    private Integer manualCnrKwd; //수동 검수 키워드 여부



}
