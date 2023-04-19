package com.example.week8.service;

import com.example.week8.dto.daddet.find.ResponseDadDetListJoinAdKwdItem;
import com.example.week8.dto.daddet.update.RequestConfirmApprovalDto;
import com.example.week8.dto.daddet.update.RequestConfirmRejectDto;
import com.example.week8.dto.daddet.update.RequestDadDetActYnDeleteAllDto;
import com.example.week8.dto.daddet.update.RequestDadDetUseConfigYnAllDto;
import com.example.week8.entity.CnrReq;
import com.example.week8.entity.DadDet;
import com.example.week8.repository.CnrReqRepository;
import com.example.week8.repository.DaddetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DaddetService {

    private final DaddetRepository daddetRepository;
    private final CnrReqRepository cnrReqRepository;

    public void updateDadUseConfigYnOnOffAll(RequestDadDetUseConfigYnAllDto requestDadDetUseConfigYnAllDto){
        daddetRepository.updateDadUseConfigYnAll2(requestDadDetUseConfigYnAllDto.getLongList(),requestDadDetUseConfigYnAllDto.getYn());

    }

    public void updateDaddetActYnDeleteAll(RequestDadDetActYnDeleteAllDto requestDadDetActYnDeleteAllDto) {
        daddetRepository.updateDadActYnAll2(requestDadDetActYnDeleteAllDto.getLongList(),0);
    }

    public List<ResponseDadDetListJoinAdKwdItem> getDaddetListsJoinAdkwdItem(String kwdName) {
        return daddetRepository.getDaddetListsJoinAdkwdItem(kwdName);
    }

    @Transactional
    public String updateConfirmReject(RequestConfirmRejectDto requestConfirmRejectDto) {
        //검수 요청
        //검수 진행 상태 : REJECT -> cnr_ing_status
        //검수 처리 시간 : 지금
        //검수 완료 여부 : Y -> cnr_complete_yn
        CnrReq cnrReq = cnrReqRepository.findById(requestConfirmRejectDto.getCnrReqId()).orElseThrow(
                ()-> new IllegalArgumentException("cnrReqId 에 맞는 값이 없습니다.")
        );
        cnrReq.updateConfirmReject();

        //직접광고 상세
        //직접광고 검수 상태 : APPROVAL -> dad_cnr_status
        DadDet dadDet = daddetRepository.findById(requestConfirmRejectDto.getDadDetId()).orElseThrow(
                ()-> new IllegalArgumentException("dadDetId에 맞는 값이 없습니다.")
        );
        dadDet.updateConfirmReject();

        return "반려 성공!";
    }

    @Transactional
    public String updateConfirmApproval(RequestConfirmApprovalDto requestConfirmApprovalDto) {
        //검수 요청
        //검수 진행 상태 : APPROVAL -> cnr_ing_status
        //검수 처리 시간 : 지금
        //검수 완료 여부 : Y -> cnr_complete_yn
        CnrReq cnrReq = cnrReqRepository.findById(requestConfirmApprovalDto.getCnrReqId()).orElseThrow(
                ()-> new IllegalArgumentException("cnrReqId 에 맞는 값이 없습니다.")
        );
        cnrReq.updateConfirmApproval();

        //직접광고 상세
        //직접광고 검수 상태 : APPROVAL -> dad_cnr_status
        DadDet dadDet = daddetRepository.findById(requestConfirmApprovalDto.getDadDetId()).orElseThrow(
                ()-> new IllegalArgumentException("dadDetId에 맞는 값이 없습니다.")
        );
        dadDet.updateConfirmApproval();

        return "승인 성공!"; //최대한 프론트로 400에러로,,,
    }
}
