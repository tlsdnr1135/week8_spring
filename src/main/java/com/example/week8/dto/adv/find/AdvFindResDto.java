package com.example.week8.dto.adv.find;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvFindResDto {

    private Integer adIngActYn; //광고 진행 활성 여부

    private Long balance; //잔액, (더미데이터 100만원)

    private Long eventMoneyBalance; //이벤트 머니 잔액 (더미데이터 0만원)

    private Long dayLimitBudget; //일 제한 예산 (더미데티어 0만원)

    @Builder
    public AdvFindResDto(Integer adIngActYn, Long balance, Long eventMoneyBalance, Long dayLimitBudget) {
        this.adIngActYn = adIngActYn;
        this.balance = balance;
        this.eventMoneyBalance = eventMoneyBalance;
        this.dayLimitBudget = dayLimitBudget;
    }
}
