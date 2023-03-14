package com.example.week8.dto.kwd.find;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KwdFindResDto {

    private String kwdName;
    private Integer sellPossKwdYn;
    private Integer manualCnrKwdYn;

    @Builder
    public KwdFindResDto(String kwdName, Integer sellPossKwdYn, Integer manualCnrKwdYn) {
        this.kwdName = kwdName;
        this.sellPossKwdYn = sellPossKwdYn;
        this.manualCnrKwdYn = manualCnrKwdYn;
    }
}
