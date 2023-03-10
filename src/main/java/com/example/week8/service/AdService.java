package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.entity.Ad;
import com.example.week8.repository.AgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AgroupRepository agroupRepository;

    @Transactional
    public Ad saveAd(AdDto adDto){
        //광고그룹 저장(ID, 광고 그룹 명)

        agroupRepository.save();


        //광고주 불러오기(ID)
        //아이디 불러오기(ID, 상품번호, 상품 명, 상품 원본 금액)

        //광고 저장
        //키워드 저장(ID)
        //직접광고 상세 입찰

        //직접광고 상세 저장
        //검수요청


        return null;
    }

}
