package com.example.week8.mapper;

import com.example.week8.dto.dadreport.find.ResponseDadReportListDto;
import com.example.week8.entity.DadReport;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DadReportMapper {

    DadReportMapper INSTANCE = Mappers.getMapper(DadReportMapper.class);

    //List<DadReport> -> List<ResponseDadReportListDto>
    @IterableMapping(qualifiedByName = "v2")
    List<ResponseDadReportListDto> toResponseDadReportListDto(List<DadReport> dadReportList);

    @Named("v2")
    @Mapping(target = "key",source = "requestDate")
    @Mapping(target = "date",source = "requestDate")
    ResponseDadReportListDto toResponseDadReportDto(DadReport dadReport);

}
