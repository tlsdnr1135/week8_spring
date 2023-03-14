package com.example.week8.dto.agroup.save;

import com.example.week8.entity.Agroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgroupSaveReqDto {

    private String agroupName;

    public Agroup toSaveAgroup(){
        return Agroup.builder()
                .agroupName(agroupName)
                .agroupActYn(1)
                .agroupUseActYn(1)
                .build();
    }

}
