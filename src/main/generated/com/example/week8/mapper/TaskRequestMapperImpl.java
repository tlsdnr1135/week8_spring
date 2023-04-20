package com.example.week8.mapper;

import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.entity.TaskRequest;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T09:29:39+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
public class TaskRequestMapperImpl implements TaskRequestMapper {

    @Override
    public List<ResponseTaskRequestDto> toResponseTaskRequestDto(List<TaskRequest> taskRequests) {
        if ( taskRequests == null ) {
            return null;
        }

        List<ResponseTaskRequestDto> list = new ArrayList<ResponseTaskRequestDto>( taskRequests.size() );
        for ( TaskRequest taskRequest : taskRequests ) {
            list.add( toItemListFindResDto( taskRequest ) );
        }

        return list;
    }

    @Override
    public ResponseTaskRequestDto toItemListFindResDto(TaskRequest taskRequest) {
        if ( taskRequest == null ) {
            return null;
        }

        ResponseTaskRequestDto responseTaskRequestDto = new ResponseTaskRequestDto();

        responseTaskRequestDto.setKey( taskRequest.getId() );
        responseTaskRequestDto.setTaskName( taskRequest.getTaskName() );
        responseTaskRequestDto.setTaskStatus( taskRequest.getTaskStatus() );
        responseTaskRequestDto.setTaskPath( taskRequest.getTaskPath() );
        if ( taskRequest.getTaskRequestTime() != null ) {
            responseTaskRequestDto.setTaskRequestTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( taskRequest.getTaskRequestTime() ) );
        }

        responseTaskRequestDto.setAdvId( taskRequest.getAdmin().getName() );

        return responseTaskRequestDto;
    }
}
