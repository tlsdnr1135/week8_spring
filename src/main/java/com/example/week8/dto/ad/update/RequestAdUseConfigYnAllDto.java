package com.example.week8.dto.ad.update;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestAdUseConfigYnAllDto {

    private List<Long> longList;

    private Integer yn;

}
