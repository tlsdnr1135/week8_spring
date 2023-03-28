package com.example.week8.entity;

import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AGROUP")
public class Agroup extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGROUP_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String agroupName; //광고그룹명

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regTime; //등록시간

    @Column(nullable = false)
    private Integer agroupActYn; //광고그룹 활성 여부

    private Integer agroupUseActYn;//광고그룹 사용 설정 여부

    @JsonManagedReference
    @OneToMany(mappedBy = "agroup",fetch = FetchType.LAZY)
    private List<Ad> ad = new ArrayList<>();


    @Builder
    public Agroup(Long id, String agroupName, LocalDateTime regTime, Integer agroupActYn, Integer agroupUseActYn) {
        this.id = id;
        this.agroupName = agroupName;
        this.regTime = regTime;
        this.agroupActYn = agroupActYn;
        this.agroupUseActYn = agroupUseActYn;
    }

    public AgroupFindResDto toAgroup(){
        return AgroupFindResDto.builder()
                .id(id)
                .agroupName(agroupName)
                .regTime(regTime)
                .agroupActYn(agroupActYn)
                .agroupUseActYn(agroupUseActYn)
//                .adList(ad)
                .build();
    }

    //agroupActYn 0 -> 1
    public void AgroupActYnStateOn(){
        this.agroupActYn = 1;
    }
    //agroupUseActYn 변경
    public void AgroupUseActYnStateChange(Integer agroupUseActYn){
        this.agroupUseActYn = agroupUseActYn;
    }

    //agroupName 변경
    public void AgroupNameChange(String str){
        this.agroupName = str;
    }
}
