package com.example.week8.dto.account.join;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResDto {

    private String name;


    public String toResDto(String str){
        name = str;
     return name;
    }

}
