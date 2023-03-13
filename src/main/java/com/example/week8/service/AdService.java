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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

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
        adDto.getAgroup().setAgroupActYn(1); //기본 값.
        adDto.getAgroup().setAgroupUseActYn(1); //기본 값.
        Agroup agroup = agroupRepository.save(adDto.getAgroup());
        System.out.println("Agroup 아이디 : "+ agroup.getId());

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

        adRepository.save(ad);
        System.out.println("광고 아이디 불러오기 :" + ad.getId());

        //키워드 저장(ID)
        //TODO 해쉬코드 재정의 하지 말고 해보기
        List<Kwd> kwds = keywordRepository.findAll(); //5, 6
        List<KwdDto> collects = adDto.getKwd(); //1 2 3 4 5

        List<KwdDto> collect;
        List<Kwd> collect2;
        if(collects != null){
            collect = collects.stream().filter((s -> !kwds.contains(s))).collect(Collectors.toList());
            collect2 = collect.stream().map(s -> s.toKwd()).collect(Collectors.toList());
        }else{
            collect = new ArrayList<>();
            collect2 = new ArrayList<>();
        }



        System.out.println("------------size --------"+collect.size());
//        System.out.println(collect2.get(0).getKwdName());


        //키워드 저장
        if(collect.size() == 0){ //키워드가 없으면 -> 0
            //패스
        }else{
            //키워드 저장(ID)
            keywordRepository.saveAll(collect2);

        }


        //검수 요청(나증에)

        //직접광고 상세 입찰
        if(collect.size() == 0) {
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
            for(int i=0; i<collect.size(); i++){
                DadDet dadDet = DadDet.builder()
                        .ad(ad)
                        .kwd(collect2.get(i))
                        .dadCnr("APPROVAL")
                        .cnrReqId(null) //검수 요청
                        .dadUseConfig(1)
                        .dadActYn(1)
                        .build();
                daddetRepository.save(dadDet);

                //직접광고 상세 저장
                DadDetBid dadDetBid = DadDetBid.builder()
                        .dadDet(dadDet)
                        .bidCost(collect.get(i).getBidCost())
                        .build();
                daddetbidRepository.save(dadDetBid);
            }
        }





        


        return null;
    }

    public Object findAll() {
        return adRepository.findAll();
    }
}
