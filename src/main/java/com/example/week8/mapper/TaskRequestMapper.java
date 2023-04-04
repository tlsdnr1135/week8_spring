package com.example.week8.mapper;

import com.example.week8.dto.item.find.ItemListFindResDto;
import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.entity.Item;
import com.example.week8.entity.TaskRequest;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskRequestMapper {

    TaskRequestMapper INSTANCE = Mappers.getMapper(TaskRequestMapper.class);

    //TaskRequest -> ResponseTaskRequestDto
    @IterableMapping(qualifiedByName = "v2")
    List<ResponseTaskRequestDto> toResponseTaskRequestDto(List<TaskRequest> taskRequests);

    @Named("v2")
    @Mapping(target = "key",source  = "id")
    @Mapping(target = "advId",expression = "java(taskRequest.getAdmin().getName())")
    ResponseTaskRequestDto toItemListFindResDto(TaskRequest taskRequest);

}
