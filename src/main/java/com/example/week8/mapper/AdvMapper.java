package com.example.week8.mapper;

import com.example.week8.dto.adv.find.AdvFindResDto;
import com.example.week8.dto.adv.update.AdvAdIngActYnResDto;
import com.example.week8.entity.Adv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdvMapper {

    AdvMapper INSTANCE = Mappers.getMapper(AdvMapper.class);

    //Adv -> AdvFindResDto
    AdvFindResDto toAdvFindResDto(Adv adv);


    //Adv ->AdvAdIngActYnResDto
    AdvAdIngActYnResDto toAdvAdIngActYnResDto(Adv adv);


}
