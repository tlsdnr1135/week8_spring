package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AGROUP")
public class Agroup extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGROUP_ID")
    private Long id;

    @Column(nullable = false)
    private String agroupName; //광고그룹명

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regTime; //등록시간

    @Column(nullable = false)
    private Integer agroupActYn; //광고그룹 활성 여부

    @Builder
    public Agroup(Long id, String agroupName, LocalDateTime regTime, Integer agroupActYn) {
        this.id = id;
        this.agroupName = agroupName;
        this.regTime = regTime;
        this.agroupActYn = agroupActYn;
    }
}
