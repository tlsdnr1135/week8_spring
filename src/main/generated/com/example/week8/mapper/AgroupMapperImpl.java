package com.example.week8.mapper;

import com.example.week8.dto.agroup.find.ResponseAgroupDetailsDto;
import com.example.week8.dto.agroup.find.ResponseAgroupDetailsDto.ResponseAgroupDetailsDtoBuilder;
import com.example.week8.entity.Agroup;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T09:29:39+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
public class AgroupMapperImpl implements AgroupMapper {

    @Override
    public ResponseAgroupDetailsDto responseAgroupDetailsDto(Agroup agroup) {
        if ( agroup == null ) {
            return null;
        }

        ResponseAgroupDetailsDtoBuilder responseAgroupDetailsDto = ResponseAgroupDetailsDto.builder();

        responseAgroupDetailsDto.key( agroup.getId() );
        responseAgroupDetailsDto.agroupName( agroup.getAgroupName() );
        responseAgroupDetailsDto.agroupUseActYn( agroup.getAgroupUseActYn() );
        responseAgroupDetailsDto.regTime( agroup.getRegTime() );

        return responseAgroupDetailsDto.build();
    }
}
