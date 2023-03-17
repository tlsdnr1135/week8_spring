package com.example.week8.dto.agroup.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgroupFindByAgroupNameLikeResDto {

    private Long agroupId;
    private String agroupName;
    private Integer agroupActYn;
    private Integer agroupUseActYn;
    private Integer adUseConfigYn;
    private Integer adActYn;

    @Builder

    public AgroupFindByAgroupNameLikeResDto(Long agroupId, String agroupName, Integer agroupActYn, Integer agroupUseActYn, Integer adUseConfigYn, Integer adActYn) {
        this.agroupId = agroupId;
        this.agroupName = agroupName;
        this.agroupActYn = agroupActYn;
        this.agroupUseActYn = agroupUseActYn;
        this.adUseConfigYn = adUseConfigYn;
        this.adActYn = adActYn;
    }
}
