package com.example.week8.mapper;

import com.example.week8.dto.agroup.find.AgroupFindByAgroupNameLikeResDto;
import com.example.week8.entity.Agroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgroupMapper {

    AgroupMapper INSTANCE = Mappers.getMapper(AgroupMapper.class);

    //agroup -> AgroupFindByAgroupNameLikeResDto
    AgroupFindByAgroupNameLikeResDto toAgroupFindByAgroupNameLikeResDto(Agroup agroup);


}
