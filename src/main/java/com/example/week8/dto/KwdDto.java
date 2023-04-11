package com.example.week8.dto;

import com.example.week8.entity.Kwd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class KwdDto {

    private String kwdName;
    private Long bidCost;

    @Builder
    public KwdDto(String kwdName, Long bidCost) {
        this.kwdName = kwdName;
        this.bidCost = bidCost;
    }

    public Kwd toKwd(){
        return Kwd.builder()
                .kwdName(kwdName)
                .sellPossKwdYn(1)
                .manualCnrKwdYn(0)
                .build();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Kwd) {
            System.out.println("KwdKwdKwdKwdKwdKwdKwdKwdKwdKwdKwdKwdKwd");
            Kwd kwd = (Kwd) obj;
            return kwd.getKwdName().equals(kwdName);
        }else {
            return false;
        }
    } // equals 동등성비교

    @Override
    public int hashCode() {
        return kwdName.hashCode();
    } // hashCode 재정의

}
