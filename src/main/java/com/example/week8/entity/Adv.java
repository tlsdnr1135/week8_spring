package com.example.week8.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Adv {

    @Id
    @Column(name = "ADV_Id")
    private String id; //회원(account)의 pk

    private Integer adIngActYn; //광고 진행 활성 여부

    private Long balance; //잔액

    private Long eventMoneyBalance; //이벤트 머니 잔액

    private Long dayLimitBudget; //일 제한 예산

}
