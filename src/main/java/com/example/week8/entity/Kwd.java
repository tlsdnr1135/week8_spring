package com.example.week8.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "KWD")
public class Kwd { //키워드

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KWD_ID")
    private Long id; //키워드 ID

    @Column(nullable = false, unique = true)
    private String kwdName; //키워드 명

    @Column(nullable = false)
    private Integer sellPossKwdYn; //판매 가능 키워드 여부 (1)

    @Column(nullable = false)
    private Integer manualCnrKwd; //수동 검수 키워드 여부 (0)



}
