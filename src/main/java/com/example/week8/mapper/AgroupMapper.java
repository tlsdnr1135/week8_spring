package com.example.week8.mapper;

import com.example.week8.dto.agroup.find.ResponseAgroupDetailsDto;
import com.example.week8.entity.Agroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgroupMapper {

    AgroupMapper INSTANCE = Mappers.getMapper(AgroupMapper.class);

    //agroup -> ResponseAgroupDetailsDto

    @Mapping(target = "key", source = "id")
    ResponseAgroupDetailsDto responseAgroupDetailsDto(Agroup agroup);



}
