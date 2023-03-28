package com.example.week8.dto.agroup.find;

import com.example.week8.entity.Ad;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AgroupFindResDto {

    private Long id;
    private String agroupName;
    private LocalDateTime regTime;

    private Integer agroupActYn;
    private Integer agroupUseActYn;

//    private List<Ad> adList;

//    @Builder
//    public AgroupFindResDto(String agroupName, LocalDateTime regTime, Integer agroupActYn, Integer agroupUseActYn, List<Ad> adList) {
//        this.agroupName = agroupName;
//        this.regTime = regTime;
//        this.agroupActYn = agroupActYn;
//        this.agroupUseActYn = agroupUseActYn;
//        this.adList = adList;
//    }
    @Builder

    public AgroupFindResDto(Long id, String agroupName, LocalDateTime regTime, Integer agroupActYn, Integer agroupUseActYn) {
        this.id = id;
        this.agroupName = agroupName;
        this.regTime = regTime;
        this.agroupActYn = agroupActYn;
        this.agroupUseActYn = agroupUseActYn;
    }
}
