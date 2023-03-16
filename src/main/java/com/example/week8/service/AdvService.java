package com.example.week8.service;

import com.example.week8.dto.adv.find.AdvFindResDto;
import com.example.week8.dto.adv.update.AdvAdIngActYnReqDto;
import com.example.week8.dto.adv.update.AdvAdIngActYnResDto;
import com.example.week8.entity.Adv;
import com.example.week8.mapper.AdvMapper;
import com.example.week8.repository.AdvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvService {

    private final AdvRepository advRepository;

    //단일조회
    public AdvFindResDto findAdv(String name){
        return AdvMapper.INSTANCE.toAdvFindResDto(advRepository.findById(name).get());
    }

    @Transactional
    //변경
    public AdvAdIngActYnResDto updateAdvAdIngActYn(AdvAdIngActYnReqDto advAdIngActYnReqDto){
        Adv adv = advRepository.findById(advAdIngActYnReqDto.getName()).orElseThrow(
                ()->new IllegalArgumentException("Adv의 이름에 해당하는 이름이 없습니다.")
        );
        adv.updateAdIngActYn(advAdIngActYnReqDto.getAdIngActYn());

        System.out.println(adv.getName());
        System.out.println(adv.getAdIngActYn());
        return AdvMapper.INSTANCE.toAdvAdIngActYnResDto(adv);
    }
}
//requsetDto 를 보통 엔티티로 바꾸잖아요
//그럼 엔티티를 save씀
//save