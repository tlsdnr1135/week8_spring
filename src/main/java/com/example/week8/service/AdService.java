package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.entity.*;
import com.example.week8.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AgroupRepository agroupRepository;
    private final AdvRepository advRepository;
    private final ItemRepository itemRepository;
    private final AdRepository adRepository;

    private final KeywordRepository keywordRepository;

    @Transactional
    public Object saveAd(AdDto adDto){
        //광고주 아이디 가져오기.
//        Adv adv = advRepository.findById(adDto.getAdv().getName()).get();
//        System.out.println("Adv 아이디 : "+ adv.getName());
//
//        //광고그룹 아이디 저장. -> 아이디 리턴 받기
//        //TODO 중복 거르기
//        adDto.getAgroup().setAgroupActYn(1); //기본 값.
//        adDto.getAgroup().setAgroupUseActYn(1); //기본 값.
//        Agroup agroup = agroupRepository.save(adDto.getAgroup());
//        System.out.println("Agroup 아이디 : "+ agroup.getId());
//
//        //아이템 아이디 가져오기.
//        Item item = itemRepository.findById(adDto.getItem().getId()).get();
//        System.out.println("Item 아이디 : "+ item.getId());
//
//        //광고 저장하기
//        //TODO 객체에 id값만 넣어주기!
//        Ad ad = Ad.builder()
//                .adv(adv)
//                .agroup(agroup)
//                .item(item)
//                .adUseConfigYn(1)
//                .adActYn(1)
//                .build();
//
//        adRepository.save(ad);
        
        //키워드 저장
        //TODO 해쉬코드 재정의 하지 말고 해보기
        List<Kwd> kwds = keywordRepository.findAll(); //5, 6

        List<String> a = new ArrayList<>();
        for(int i=0; i<kwds.size(); i++) {
            a.add(kwds.get(i).getKwdName());
        }

        List<Kwd> collect = adDto.getKwd(); //1 2 3 4 5
        List<String> b = new ArrayList<>();
        for(int i=0; i<collect.size(); i++) {
            b.add(collect.get(i).getKwdName());
        }
        List<String> c = new ArrayList<>();
        for(int i=0; i<collect.size(); i++) {
            c.add(collect.get(i).getKwdName());
        }

        for(int i=0; i<a.size(); i++){
            if(a.contains(b.get(i))){
                c.remove(b.get(i));
            }
        }

        List<Kwd> kwdList = new ArrayList<>();
        for(int i=0; i<c.size(); i++){

            Kwd kwd = Kwd.builder()
                    .kwdName(c.get(i))
                    .build();
            kwdList.add(kwd);
        }

        System.out.println("------------size --------"+kwdList.size());

        if(kwdList.size() == 0){
            return null;
        }
        keywordRepository.saveAll(kwdList);

        
        //광고 저장
        //키워드 저장(ID)
        //직접광고 상세 입찰

        //직접광고 상세 저장
        //검수요청


        return null;
    }

    public Object findAll() {
        return adRepository.findAll();
    }
}
