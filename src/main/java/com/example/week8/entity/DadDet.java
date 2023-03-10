package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "DAD_DET")
public class DadDet extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DAD_DET_ID")
    private Long id; //직접광고 상세ID

    @ManyToOne
    @JoinColumn(name = "AD_ID",nullable = false)
    private Ad ad;//광고 ID(FK)
    @ManyToOne
    @JoinColumn(name = "KWD",nullable = false)
    private Kwd kwdId;//키워드 ID(FK)

    @Column(nullable = false)
    private String dadCnr;//직접광고 검수 상태, 직접광고 검수 상태: APPROVAL(추후에 REJECT도 사용)

    @OneToOne
    @JoinColumn(name = "CnrReq",nullable = false)
    private CnrReq cnrReqId;//검수 요청 ID(FK), 신규로 적재될 검수 요청ID 기재

    @Column(nullable = false)
    private Integer dadUseConfig;//직접광고 사용 설정 여부

    @Column(nullable = false)
    private Integer dadActYn;//직접광고 활성 여부
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regTime;//등록 시간


//    @Builder
//    public DadDet(Long id, Long adId, Long kwdId, String dadCnr, Long cnrReqId, Integer dadUseConfig, Integer dadActYn, LocalDateTime regTime) {
//        this.id = id;
//        this.adId = adId;
//        this.kwdId = kwdId;
//        this.dadCnr = dadCnr;
//        this.cnrReqId = cnrReqId;
//        this.dadUseConfig = dadUseConfig;
//        this.dadActYn = dadActYn;
//        this.regTime = regTime;
//    }

}
