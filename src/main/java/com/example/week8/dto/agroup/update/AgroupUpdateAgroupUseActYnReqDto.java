package com.example.week8.dto.agroup.update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AgroupUpdateAgroupUseActYnReqDto {

    private String agroupName;

    @Builder

    public AgroupUpdateAgroupUseActYnReqDto(String agroupName) {
        this.agroupName = agroupName;
    }
}
