package com.example.week8.service;

import com.example.week8.dto.adv.find.AdvFindResDto;
import com.example.week8.dto.adv.update.AdvAdIngActYnReqDto;
import com.example.week8.dto.adv.update.AdvAdIngActYnResDto;
import com.example.week8.dto.adv.update.AdvDayLimitBudgetReqDto;
import com.example.week8.dto.adv.update.AdvDayLimitBudgetResDto;
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
    //변경(광고활성여부)
    public AdvAdIngActYnResDto updateAdvAdIngActYn(AdvAdIngActYnReqDto advAdIngActYnReqDto){
        Adv adv = advRepository.findById(advAdIngActYnReqDto.getName()).orElseThrow(
                ()->new IllegalArgumentException("Adv의 이름에 해당하는 이름이 없습니다.")
        );
        adv.updateAdIngActYn(advAdIngActYnReqDto.getAdIngActYn());

        System.out.println(adv.getName());
        System.out.println(adv.getAdIngActYn());
        return AdvMapper.INSTANCE.toAdvAdIngActYnResDto(adv);
    }

    @Transactional
    //변경(일 제한 예산)
    public AdvDayLimitBudgetResDto updateDayLimitBudget(AdvDayLimitBudgetReqDto advDayLimitBudgetReqDto){
        Adv adv = advRepository.findById(advDayLimitBudgetReqDto.getName()).orElseThrow(
                ()->new IllegalArgumentException("Adv의 이름에 해당하는 이름이 없습니다.")
        );
        adv.updateDayLimitBudget(advDayLimitBudgetReqDto.getDayLimitBudget());

        System.out.println(adv.getName());
        System.out.println(adv.getDayLimitBudget());
        return AdvMapper.INSTANCE.toAdvDayLimitBudgetResDto(adv);
    }
}
