package com.example.week8.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "AD")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AD_ID")
    private Long id; //광고 ID

    private Long agroupId; //광고 그룹 ID (FK)

    private Long itemId; //상품 ID (FK)
    private Integer adUseConfigYn; //광고 사용 설정 여부

    @CreatedDate
    private LocalDateTime regTime; //등록 시간

    private Integer adActYn; //광고 활성 여부

    private String advId; //광고주 ID (FK)


}
