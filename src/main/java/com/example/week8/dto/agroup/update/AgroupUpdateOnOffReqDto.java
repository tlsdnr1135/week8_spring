package com.example.week8.dto.agroup.update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AgroupUpdateOnOffReqDto {

    private List<Long> longList;

    private Integer yn;

    @Builder
    public AgroupUpdateOnOffReqDto(List<Long> longList, Integer yn) {
        this.longList = longList;
        this.yn = yn;
    }
}
