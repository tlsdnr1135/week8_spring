package com.example.week8.entity;


import com.example.week8.dto.KwdDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "KWD")
public class Kwd { //키워드

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KWD_ID")
    private Long id; //키워드 ID

    @Column(nullable = false, unique = true)
    private String kwdName; //키워드 명

    @Column(nullable = false)
    private Integer sellPossKwdYn = 1; //판매 가능 키워드 여부 (1)

    @Column(nullable = false)
    private Integer manualCnrKwdYn = 0; //수동 검수 키워드 여부 (0)
    @Builder
    public Kwd(Long id, String kwdName, Integer sellPossKwdYn, Integer manualCnrKwdYn) {
        this.id = id;
        this.kwdName = kwdName;
        this.sellPossKwdYn = 1;
        this.manualCnrKwdYn = 0;
    }

    public boolean containsCheck(List<Kwd> kwds){
        return kwds.stream().filter(kwd -> kwd.getKwdName().equals(kwdName)).count() != 0;


//        if(kwds.contains(kwdName)){
//            return true;
//        }else{
//            return false;
//        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof KwdDto) {
            KwdDto kwd = (KwdDto) obj;
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
