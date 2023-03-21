package com.example.week8.dto.agroup.update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgroupUpdateAgroupNameReqDto {

    private String beforeAgroupName;
    private String afterAgroupName;

    @Builder
    public AgroupUpdateAgroupNameReqDto(String beforeAgroupName, String afterAgroupName) {
        this.beforeAgroupName = beforeAgroupName;
        this.afterAgroupName = afterAgroupName;
    }
}
