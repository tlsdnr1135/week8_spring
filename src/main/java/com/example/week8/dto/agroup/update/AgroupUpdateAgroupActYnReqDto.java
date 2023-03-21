package com.example.week8.dto.agroup.update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AgroupUpdateAgroupActYnReqDto {

    private List<Long> longList;

    @Builder
    public AgroupUpdateAgroupActYnReqDto(List<Long> longList) {
        this.longList = longList;
    }
}
