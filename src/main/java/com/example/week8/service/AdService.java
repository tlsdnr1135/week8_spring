package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.KwdDto;
import com.example.week8.entity.*;
import com.example.week8.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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

    @Transactional
    public Object saveAd(AdDto adDto){
        //광고주 아이디 가져오기.
        Adv adv = advRepository.findById(adDto.getAdv().getName()).get();
        System.out.println("Adv 아이디 : "+ adv.getName());

        //광고그룹 아이디 저장. -> 아이디 리턴 받기
        //TODO 중복 거르기
        Agroup findAgroup = agroupRepository.findByAgroupName(adDto.getAgroup().getAgroupName()).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하는 광고그룹 아이디 입니다.")
        );
        if(findAgroup == null){
            adDto.getAgroup().setAgroupActYn(1); //기본 값.
            adDto.getAgroup().setAgroupUseActYn(1); //기본 값.
            findAgroup = agroupRepository.save(adDto.getAgroup());
            System.out.println("Agroup 아이디 : "+ findAgroup.getId());
        }


        //아이템 아이디 가져오기.
        Item item = itemRepository.findById(adDto.getItem().getId()).get();
        System.out.println("Item 아이디 : "+ item.getId());

        //광고 저장하기
        //TODO 객체에 id값만 넣어주기!
        Ad ad = Ad.builder()
                .adv(adv)
                .agroup(findAgroup)
                .item(item)
                .adUseConfigYn(1)
                .adActYn(1)
                .build();
        //연관관계저장 ad->agroup
        //1. 전체 객체를 넣고 출력
        findAgroup.getAd().add(ad);

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
        List<Kwd> kwds = new ArrayList<>();;

        //키워드 저장하기
        if (collects != null){ //키워드 값이 null이 아니면
            for(int i=0; i<collects.size(); i++){
                if(!dbKwds.contains(collects.get(i))){ //중복이 안된다면
                    //데이터 인서트
                    Kwd entity = keywordRepository.save(collects.get(i).toKwd());
                    kwds.add(entity);
                }else { //중복이면
                    Kwd kwd =keywordRepository.findByKwdName(collects.get(i).getKwdName());
                    kwds.add(kwd);
                }
            }
        }
        for(int i=0; i<kwds.size(); i++){
            System.out.println(kwds.get(i).getId());
        }

        //검수 요청(나증에)

        //직접광고 상세 입찰
        if(collects == null) {
            //키워드 없으면 광고만 등록
            //직접광고 상세
            DadDet dadDet = DadDet.builder()
                    .ad(ad)
                    .kwd(null)
                    .dadCnr("APPROVAL")
                    .cnrReqId(null) //검수 요청
                    .dadUseConfig(1)
                    .dadActYn(1)
                    .build();
            daddetRepository.save(dadDet);

            //직접광고 상세 저장
            DadDetBid dadDetBid = DadDetBid.builder()
                    .dadDet(dadDet)
                    .bidCost(0L) //키워드 없어서 입찰금액 없음.
                    .build();

            daddetbidRepository.save(dadDetBid);
        }else{
            //키워드 있으면 광고와 키워드 등록
            //스트림으로 해도 될듯?
            for(int i=0; i<kwds.size(); i++){
                DadDet dadDet = DadDet.builder()
                        .ad(ad)
                        .kwd(kwds.get(i))
                        .dadCnr("APPROVAL")
                        .cnrReqId(null) //검수 요청
                        .dadUseConfig(1)
                        .dadActYn(1)
                        .build();
                daddetRepository.save(dadDet);

                //직접광고 상세 저장
                DadDetBid dadDetBid = DadDetBid.builder()
                        .dadDet(dadDet)
                        .bidCost(collects.get(i).getBidCost()) //dto
                        .build();
                daddetbidRepository.save(dadDetBid);
            }
        }
        System.out.println("ssssssssssssssssssssssssssssssssssssssss");
        return null;
    }

    public Object findAll() {
        return adRepository.findAll();
    }
}
