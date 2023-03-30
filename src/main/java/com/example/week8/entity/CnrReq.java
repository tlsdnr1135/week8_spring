package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "CNR_REQ")
public class CnrReq extends Timestamped{ //검수 요청

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CNR_REQ_ID")
    private Long id; //상품 아이디

    //TODO 직접광고 상세 ID는 PK가 아닌가?
    @OneToOne(mappedBy = "cnrReq")
//    @Column(nullable = false)
    private DadDet dadDet;//직접광고 상세 ID

    @Column(nullable = false)
    private String cnrIngStatus;//검수 진행상태

    @Column(nullable = false)
    private String cnrInputDiv;//검수 입력구분

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime cnrReqTime;//검수 요청시간

//    @LastModifiedDate
    private LocalDateTime cnrProcTime; //검수 처리시간

    @Column(nullable = false)
    private Integer cnrComplete; //검수 완료여부

    private String cnrFailCause; //검수 실패사유
    private String cnrFailComt;//검수 실패 코멘트

    @Builder
    public CnrReq(Long id, DadDet dadDetId, String cnrIngStatus, String cnrInputDiv, LocalDateTime cnrReqTime, LocalDateTime cnrProcTime, Integer cnrComplete, String cnrFailCause, String cnrFailComt) {
        this.id = id;
        this.dadDet = dadDetId;
        this.cnrIngStatus = cnrIngStatus;
        this.cnrInputDiv = cnrInputDiv;
        this.cnrReqTime = cnrReqTime;
        this.cnrProcTime = cnrProcTime;
        this.cnrComplete = cnrComplete;
        this.cnrFailCause = cnrFailCause;
        this.cnrFailComt = cnrFailComt;
    }

    //직접광고 상세 ID
    public void setDadDet(DadDet dadDet){
        this.dadDet = dadDet;
    }
    //처리시간 널 처리
    public void setcnrProcTime(LocalDateTime cnrProcTime){
        this.cnrProcTime = cnrProcTime;
    }


}
