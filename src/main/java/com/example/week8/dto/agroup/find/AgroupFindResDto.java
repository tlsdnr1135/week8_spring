package com.example.week8.dto.agroup.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AgroupFindResDto {

    private String agroupName;
    private LocalDateTime regTime;

    private Integer agroupActYn;
    private Integer agroupUseActYn;

    @Builder
    public AgroupFindResDto(String agroupName, LocalDateTime regTime, Integer agroupActYn, Integer agroupUseActYn) {
        this.agroupName = agroupName;
        this.regTime = regTime;
        this.agroupActYn = agroupActYn;
        this.agroupUseActYn = agroupUseActYn;
    }
}
