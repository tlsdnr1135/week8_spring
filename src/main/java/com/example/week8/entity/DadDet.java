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
    @JoinColumn(name = "KWD_ID")
    private Kwd kwd;//키워드 ID(FK)

    @Column(nullable = false)
    private String dadCnrStatus;//직접광고 검수 상태, 직접광고 검수 상태: APPROVAL(추후에 REJECT도 사용)

    @OneToOne
//    @JoinColumn(name = "CnrReq",nullable = false)
    @JoinColumn(name = "CNR_REQ_ID")
    private CnrReq cnrReq;//검수 요청 ID(FK), 신규로 적재될 검수 요청ID 기재

    @Column(nullable = false)
    private Integer dadUseConfigYn;//직접광고 사용 설정 여부

    @Column(nullable = false)
    private Integer dadActYn;//직접광고 활성 여부
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regTime;//등록 시간


    @Builder
    public DadDet(Long id, Ad ad, Kwd kwd, String dadCnr, CnrReq cnrReqId, Integer dadUseConfigYn, Integer dadActYn, LocalDateTime regTime) {
        this.id = id;
        this.ad = ad;
        this.kwd = kwd;
        this.dadCnrStatus = dadCnr;
        this.cnrReq = cnrReqId;
        this.dadUseConfigYn = dadUseConfigYn;
        this.dadActYn = dadActYn;
        this.regTime = regTime;
    }

    public void setCnrReq(CnrReq cnrReq){
     this.cnrReq = cnrReq;
    }
    public void updateConfirmReject(){
        this.dadCnrStatus="APPROVAL";
    }
    public void updateConfirmApproval(){
        this.dadCnrStatus="APPROVAL";
    }

}
