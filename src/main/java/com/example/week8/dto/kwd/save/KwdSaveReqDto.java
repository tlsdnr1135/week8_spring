package com.example.week8.dto.kwd.save;

import com.example.week8.entity.Kwd;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KwdSaveReqDto {

    private String kwdName;

    public Kwd toFindKwd(){
        return Kwd.builder()
                .kwdName(kwdName)
                .sellPossKwdYn(1)
                .manualCnrKwdYn(0)
                .build();
    }

}
