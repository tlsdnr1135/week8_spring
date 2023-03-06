package com.example.week8.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "AGROUP")
public class Agroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGROUP_ID")
    private Long id;

    private String agroupName;
    @CreatedDate
    private LocalDateTime regTime;
    private Integer agroupActYn;


}
