package com.example.week8.dto.kwd.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestManualOffDto {
    private Long id;

    @Builder
    public RequestManualOffDto(Long id) {
        this.id = id;
    }
}
