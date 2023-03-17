package com.example.week8.service;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.example.week8.dto.agroup.save.AgroupSaveReqDto;
import com.example.week8.entity.Agroup;
import com.example.week8.mapper.AgroupMapper;
import com.example.week8.repository.AgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgroupService {


    private final AgroupRepository agroupRepository;

    //조회(전체)
    public List<AgroupFindResDto> findAgroup() {
        return agroupRepository.findAll().stream().map(s -> s.toAgroup()).collect(Collectors.toList());
    }
    //조회(LIKE 이름)
    public List<AgroupFindResDto> findAgroupAggl(String agroupName) {
        List<Agroup> agroups = agroupRepository.findByAgroupNameLike(agroupName);
//        AgroupMapper.INSTANCE.toAgroupFindByAgroupNameLikeResDto(agroup)
        return null;
    }

    
    //저장
    public AgroupFindResDto saveAgroup(AgroupSaveReqDto agroupSaveReqDto) {
        Agroup agroup = agroupRepository.save(agroupSaveReqDto.toSaveAgroup());
        return agroup.toAgroup();
    }


}
