package com.example.week8.dto.kwd.save;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KwdSaveResDto {

    private Long id;
    private String kwdName;

    @Builder
    public KwdSaveResDto(Long id, String kwdName) {
        this.id = id;
        this.kwdName = kwdName;
    }
}
