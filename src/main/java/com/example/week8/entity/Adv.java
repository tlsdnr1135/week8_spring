package com.example.week8.entity;

import com.example.week8.enums.AccountRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "ADV")
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "ADV_ID")
public class Adv extends Account{

    private Integer adIngActYn; //광고 진행 활성 여부

    private Long balance; //잔액, (더미데이터 100만원)

    private Long eventMoneyBalance; //이벤트 머니 잔액 (더미데이터 0만원)

    private Long dayLimitBudget; //일 제한 예산 (더미데티어 0만원)

    @Builder
    public Adv(String name, String password, AccountRoleEnum role, Integer adIngActYn, Long balance, Long eventMoneyBalance, Long dayLimitBudget) {
        super(name, password, role);
        this.adIngActYn = adIngActYn;
        this.balance = balance;
        this.eventMoneyBalance = eventMoneyBalance;
        this.dayLimitBudget = dayLimitBudget;
    }
}
