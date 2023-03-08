package com.example.week8.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AgroupDto {

    @NotNull
    private String agroupName; //광고 그룹명

}
