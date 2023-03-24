package com.example.week8.service;

import com.example.week8.dto.daddet.update.RequestDadDetActYnDeleteAllDto;
import com.example.week8.dto.daddet.update.RequestDadDetUseConfigYnAllDto;
import com.example.week8.repository.DaddetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DaddetService {

    private final DaddetRepository daddetRepository;
    public void updateDadUseConfigYnOnOffAll(RequestDadDetUseConfigYnAllDto requestDadDetUseConfigYnAllDto){
        daddetRepository.updateDadUseConfigYnAll2(requestDadDetUseConfigYnAllDto.getLongList(),requestDadDetUseConfigYnAllDto.getYn());

    }

    public void updateDaddetActYnDeleteAll(RequestDadDetActYnDeleteAllDto requestDadDetActYnDeleteAllDto) {
        daddetRepository.updateDadActYnAll2(requestDadDetActYnDeleteAllDto.getLongList(),0);
    }
}
