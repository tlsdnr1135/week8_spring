package com.example.week8.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "AD")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AD_ID")
    private Long id; //광고 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADV_ID",nullable = false)
    private Adv adv; //광고주 ID (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGROUP_ID",nullable = false)
    private Agroup agroup;//광고 그룹 ID (FK)

    @ManyToOne
    @JoinColumn(name = "ITEM_ID",nullable = false)
    private Item item; //상품 ID (FK)

    @Column(nullable = false)
    private Integer adUseConfigYn; //광고 사용 설정 여부

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regTime; //등록 시간

    @Column(nullable = false)
    private Integer adActYn; //광고 활성 여부



}
