package com.example.week8.dto.agroup.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAgroupDetailsDto {

    private Long key;
    private String agroupName;
    private Integer adUseConfigYn;
    private Integer agroupUseActYn;
    private LocalDateTime regTime;

    @Builder
    public ResponseAgroupDetailsDto(Long key, String agroupName, Integer adUseConfigYn, Integer agroupUseActYn, LocalDateTime regTime) {
        this.key = key;
        this.agroupName = agroupName;
        this.adUseConfigYn = adUseConfigYn;
        this.agroupUseActYn = agroupUseActYn;
        this.regTime = regTime;
    }
}
