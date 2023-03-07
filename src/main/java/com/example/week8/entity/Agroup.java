package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "AGROUP")
public class Agroup extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGROUP_ID")
    private Long id;

    private String agroupName;

    @CreatedDate
    private LocalDateTime regTime;

    private Integer agroupActYn;

    @Builder
    public Agroup(Long id, String agroupName, LocalDateTime regTime, Integer agroupActYn) {
        this.id = id;
        this.agroupName = agroupName;
        this.regTime = regTime;
        this.agroupActYn = agroupActYn;
    }
}
