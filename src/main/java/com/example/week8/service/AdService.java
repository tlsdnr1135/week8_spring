package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.KwdDto;
import com.example.week8.dto.ad.update.RequestAdActYnAllDto;
import com.example.week8.dto.ad.update.RequestAdUseConfigYnAllDto;
import com.example.week8.dto.ad.update.ResponseCurrentStateAdListDto;
import com.example.week8.entity.*;
import com.example.week8.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdService {

    private final AgroupRepository agroupRepository;
    private final AdvRepository advRepository;
    private final ItemRepository itemRepository;
    private final AdRepository adRepository;

    private final KeywordRepository keywordRepository;
    private final DaddetRepository daddetRepository;
    private final DaddetbidRepository daddetbidRepository;
    private final CnrReqRepository cnrReqRepository;

    @Transactional
    public Object saveAd(AdDto adDto){
        System.out.println(adDto.getItem().getId());
        //광고주 아이디 가져오기.
        Adv adv = advRepository.findById(adDto.getAdv().getName()).get();

        //광고그룹 아이디 저장. -> 아이디 리턴 받기
        //중복 체크
        Optional<Agroup> agroupOp = agroupRepository.findByAgroupName(adDto.getAgroup().getAgroupName());
        Long agroupId;
        if(agroupOp.isPresent()){ //값이 존재하면
            agroupId = agroupOp.get().getId(); //아이디 바로 가져오기
        }else{
            adDto.getAgroup().setAgroupActYn(1); //기본 값.
            adDto.getAgroup().setAgroupUseActYn(1); //기본 값.
            agroupId = agroupRepository.save(adDto.getAgroup()).getId();
        }
        Agroup agroup = Agroup.builder()
                .id(agroupId)
                .build();

        //아이템 아이디 가져오기.
        Item item = itemRepository.findById(adDto.getItem().getId()).get();
        System.out.println("Item 아이디 : "+ item.getId());

        //광고 저장하기
        //TODO 객체에 id값만 넣어주기!
        Ad ad = Ad.builder()
                .adv(adv)
                .agroup(agroup)
                .item(item)
                .adUseConfigYn(1)
                .adActYn(1)
                .build();
        //연관관계저장 ad->agroup

        //1. 전체 객체를 넣고 출력
        agroup.getAd().add(ad); //현재 내가 직접 만든 객체

        //2. 가짜 객체를 넣고 출력
//        Ad ad1 = Ad.builder()
//                .id(99L)
//                .build();
//        findAgroup.getAd().add(ad1);
        adRepository.save(ad);


        System.out.println("광고 아이디 불러오기 :" + ad.getId());

        //키워드 저장(ID)
        List<Kwd> dbKwds = keywordRepository.findAll(); //5, 6 키워드 저장
        List<KwdDto> collects = adDto.getKwd(); //1 2 3 4 5
        List<Kwd> kwds = new ArrayList<>();

        //키워드 저장하기
        for(KwdDto kwdDto: collects){
            Optional<Kwd> kwd = keywordRepository.findByKwdName(kwdDto.toKwd().getKwdName());
            if(kwd.isEmpty()){ //키워드가 디비에 없으면
                //데이터 인서트
                Kwd entity = keywordRepository.save(kwdDto.toKwd());
                kwds.add(entity);
            }else{ //중복이면
                kwds.add(kwd.get());
            }
        }

        for (Kwd kwd : kwds) {
            System.out.println("키워드 아이디 값 : " + kwd.getId());
        }

        //키워드 판매 가능 키워드 false인것 확인
        for(Kwd kwd:kwds){
            if(kwd.getSellPossKwdYn() == 0){
                throw new RuntimeException("판매 가능 키워드가 False입니다.");
            }
        }

        System.out.println("----------------확인-------------------");
        System.out.println("DTO "+collects.size());
        System.out.println("진짜 배열 "+kwds.size());
        System.out.println("----------------확인-------------------");

        //키워드 개수에 따라 직접광고상세 만들기
        for(int i=0; i<kwds.size(); i++){
            //더미
//            DadDet temp = DadDet.builder()
//                    .id(0L)
//                    .build();

            if(kwds.get(i).getManualCnrKwdYn() == 1){ //수동
                System.out.println("여기가 수동");

                //검수 요청
                CnrReq cnrReq = CnrReq.builder()
                        .dadDetId(new DadDet()) //직접광고 상세 ID
                        .cnrIngStatus("REQ") //검수 진행 상태
                        .cnrInputDiv("INPUT_CNR") //검수 입력 구분
                        .cnrProcTime(null) //처리시간
                        .cnrCompleteYn(0) //검수 완료 여부(N)
                        .cnrFailCause(null) //검수 실패 사유
                        .cnrFailComt(null) //검수 실패 코멘트
                        .build();
                cnrReqRepository.save(cnrReq); //나중에 광고 상세 아이디 넣어줘야함

                //직접광고 상세
                DadDet dadDet = DadDet.builder()
                        .ad(ad)
                        .kwd(kwds.get(i)) //키워드
                        .dadCnr("REQ") //직접광고 검수 상태
                        .cnrReqId(cnrReq) //검수 요청
                        .dadUseConfigYn(1)
                        .dadActYn(1)
                        .build();
                daddetRepository.save(dadDet);
                cnrReq.setDadDet(dadDet); //빈 객체를 갈아 끼워줌

                //직접광고 상세 입찰
                DadDetBid dadDetBid = DadDetBid.builder()
                        .dadDet(dadDet)
                        .bidCost(collects.get(i).getBidCost()) //키워드 없어서 입찰금액 없음.
                        .build();
                daddetbidRepository.save(dadDetBid);

            }else if(kwds.get(i).getManualCnrKwdYn() == 0){ //일반
                System.out.println("여기가 일반");
                //검수 요청
                CnrReq cnrReq = CnrReq.builder()
                        .dadDetId(new DadDet()) //직접광고 상세 ID
                        .cnrIngStatus("APPROVAL") //검수 진행 상태
                        .cnrInputDiv("INPUT_CNR") //검수 입력 구분
                        .cnrCompleteYn(1) //검수 완료 여부(N)
                        .cnrFailCause(null) //검수 실패 사유
                        .cnrFailComt(null) //검수 실패 코멘트
                        .build();
                cnrReqRepository.save(cnrReq); //나중에 광고 상세 아이디 넣어줘야함
                cnrReq.setcnrProcTime(cnrReq.getCnrReqTime()); //처리시간 null 처리
                //직접광고 상세
                DadDet dadDet = DadDet.builder()
                        .ad(ad)
                        .kwd(kwds.get(i)) //키워드
                        .dadCnr("APPROVAL") //직접광고 검수 상태
                        .cnrReqId(cnrReq) //검수 요청
                        .dadUseConfigYn(1)
                        .dadActYn(1)
                        .build();
                daddetRepository.save(dadDet);
                cnrReq.setDadDet(dadDet); //빈 객체를 갈아 끼워줌

                //직접광고 상세 입찰
                DadDetBid dadDetBid = DadDetBid.builder()
                        .dadDet(dadDet)
                        .bidCost(collects.get(i).getBidCost()) //키워드 없어서 입찰금액 없음.
                        .build();
                daddetbidRepository.save(dadDetBid);
            }
        }
        System.out.println("------------------------------------------------------------");
        return null;
    }

    public Object findAll() {
        return adRepository.findAll();
    }

    //update
    //OnOff버튼(AdUseConfigYn, DadUseConfigYn)
    @Transactional
    public void updateAdUseConfigYnALl(RequestAdUseConfigYnAllDto requestAdUseConfigYnAllDto){
        adRepository.updateAdUseConfigYnAll(requestAdUseConfigYnAllDto.getLongList(),requestAdUseConfigYnAllDto.getYn());
        daddetRepository.updateDadUseConfigYnAll(requestAdUseConfigYnAllDto.getLongList(),requestAdUseConfigYnAllDto.getYn());
    }

    //delete
    //그룹 삭제 버튼(AdUseConfigYn, DadUseConfigYn)
    @Transactional
    public void updateAdActYnALl(RequestAdActYnAllDto requestAdActYnAllDto){
        adRepository.updateAdActYnALl(requestAdActYnAllDto.getLongList(),requestAdActYnAllDto.getYn());
        daddetRepository.updateDadActYnAll(requestAdActYnAllDto.getLongList(),requestAdActYnAllDto.getYn());
    }

    //광고 현황 테이블
    public List<ResponseCurrentStateAdListDto> findCurrentStateAdLists() {
        return adRepository.findCurrentStateAdLists();
    }
}
