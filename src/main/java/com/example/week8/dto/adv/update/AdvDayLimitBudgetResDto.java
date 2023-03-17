package com.example.week8.dto.adv.update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvDayLimitBudgetResDto {

    private String name; //이름
    private Long dayLimitBudget; //일 제한 예산 (더미데티어 0만원)

    @Builder
    public AdvDayLimitBudgetResDto(String name, Long dayLimitBudget) {
        this.name = name;
        this.dayLimitBudget = dayLimitBudget;
    }
}
