package com.example.week8.service;

import com.example.week8.dto.ad.find.AdManageDto;
import com.example.week8.dto.agroup.find.ResponseAgroupDetailsDto;
import com.example.week8.dto.agroup.update.AgroupUpdateAgroupActYnReqDto;
import com.example.week8.dto.agroup.update.AgroupUpdateAgroupNameReqDto;
import com.example.week8.dto.agroup.update.AgroupUpdateAgroupUseActYnReqDto;
import com.example.week8.dto.agroup.update.AgroupUpdateOnOffReqDto;
import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.example.week8.dto.agroup.save.AgroupSaveReqDto;
import com.example.week8.entity.Agroup;
import com.example.week8.mapper.AgroupMapper;
import com.example.week8.repository.AgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgroupService {


    private final AgroupRepository agroupRepository;

    //조회(전체)
    public List<AgroupFindResDto> findAgroup() {
        return agroupRepository.findAll().stream().map(s -> s.toAgroup()).collect(Collectors.toList());
    }

    //상세조회
    public ResponseAgroupDetailsDto findDetails(Long agroupId) {
        Agroup agroup = agroupRepository.findById(agroupId).orElseThrow(
                ()->new IllegalArgumentException("해당하는 아이디가 없습니다.")
        );
        ResponseAgroupDetailsDto responseAgroupDetailsDto = AgroupMapper.INSTANCE.responseAgroupDetailsDto(agroup);
        return responseAgroupDetailsDto;
    }

    //그룹 리스트 조회
    public List<AdManageDto> findBySulbin(String name, String agroupName) {
        return agroupRepository.findByAgroupListJoinAd(name,agroupName);
    }

    //저장
    @Transactional
    public AgroupFindResDto saveAgroup(AgroupSaveReqDto agroupSaveReqDto) {
        Agroup agroup;
        //아이디 있는지 확인
        if(agroupRepository.existsByAgroupName(agroupSaveReqDto.getAgroupName())){
            //있으면
            agroup = agroupRepository.findByAgroupName(agroupSaveReqDto.getAgroupName()).orElseThrow(
                    ()-> new IllegalArgumentException("없는 아이디")
            );
            agroup.AgroupActYnStateOn();
        }else {
            //없으면
            agroup = agroupRepository.save(agroupSaveReqDto.toSaveAgroup());
        }
        return agroup.toAgroup();
    }

    //OnOff
    public void updateOnOff(AgroupUpdateOnOffReqDto agroupFindOnOffReqDto){
        agroupRepository.updateOnOff(agroupFindOnOffReqDto.getLongList(),agroupFindOnOffReqDto.getYn());
    }

    //agroupUseActYn 변경
    @Transactional
    public Integer updateAgroupAgroupUseActYn(AgroupUpdateAgroupUseActYnReqDto agroupUpdateAgroupUseActYnReqDto){
        Agroup agroup = agroupRepository.findByAgroupName(agroupUpdateAgroupUseActYnReqDto.getAgroupName()).orElseThrow(
                ()->new IllegalArgumentException("없는 아이디")
        );
        if(agroup.getAgroupUseActYn()==1){
            agroup.AgroupUseActYnStateChange(0);
        }else{
            agroup.AgroupUseActYnStateChange(1);
        }
        return agroup.getAgroupUseActYn();
    }


    public void updateAgroupActYn(AgroupUpdateAgroupActYnReqDto agroupUpdateAgroupActYnReqDto) {
        agroupRepository.updateAgroupActYnOff(agroupUpdateAgroupActYnReqDto.getLongList());
    }

    @Transactional
    public String updateAgroupAgroupName(AgroupUpdateAgroupNameReqDto updateAgroupNameReqDto) {
        System.out.println("기존 아이디"+updateAgroupNameReqDto.getBeforeAgroupName());
        System.out.println("바꿀 아이디"+updateAgroupNameReqDto.getAfterAgroupName());
        if(agroupRepository.existsByAgroupName(updateAgroupNameReqDto.getAfterAgroupName())) {//바꿀 아이디 검사
           new IllegalArgumentException("아이디가 존재합니다.");
        }
        Agroup agroup = agroupRepository.findByAgroupName(updateAgroupNameReqDto.getBeforeAgroupName()).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        agroup.AgroupNameChange(updateAgroupNameReqDto.getAfterAgroupName());
        return agroup.getAgroupName();
    }


}
