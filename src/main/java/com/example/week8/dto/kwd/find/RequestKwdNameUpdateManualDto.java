package com.example.week8.dto.kwd.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestKwdNameUpdateManualDto {

    private String keywordName;

    @Builder
    public RequestKwdNameUpdateManualDto(String keywordName) {
        this.keywordName = keywordName;
    }
}
