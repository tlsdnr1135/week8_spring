package com.example.week8.dto.adv.update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvAdIngActYnResDto {

    private String name; //이름

    private Integer adIngActYn; //광고 진행 활성 여부

    private Long balance; //잔액, (더미데이터 100만원)

    private Long eventMoneyBalance; //이벤트 머니 잔액 (더미데이터 0만원)

    private Long dayLimitBudget; //일 제한 예산 (더미데티어 0만원)

    @Builder

    public AdvAdIngActYnResDto(String name, Integer adIngActYn, Long balance, Long eventMoneyBalance, Long dayLimitBudget) {
        this.name = name;
        this.adIngActYn = adIngActYn;
        this.balance = balance;
        this.eventMoneyBalance = eventMoneyBalance;
        this.dayLimitBudget = dayLimitBudget;
    }
}
