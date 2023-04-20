package com.example.week8.mapper;

import com.example.week8.dto.dadreport.find.ResponseDadReportListDto;
import com.example.week8.dto.dadreport.find.ResponseDadReportListDto.ResponseDadReportListDtoBuilder;
import com.example.week8.entity.DadReport;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T09:29:39+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
public class DadReportMapperImpl implements DadReportMapper {

    @Override
    public List<ResponseDadReportListDto> toResponseDadReportListDto(List<DadReport> dadReportList) {
        if ( dadReportList == null ) {
            return null;
        }

        List<ResponseDadReportListDto> list = new ArrayList<ResponseDadReportListDto>( dadReportList.size() );
        for ( DadReport dadReport : dadReportList ) {
            list.add( toResponseDadReportDto( dadReport ) );
        }

        return list;
    }

    @Override
    public ResponseDadReportListDto toResponseDadReportDto(DadReport dadReport) {
        if ( dadReport == null ) {
            return null;
        }

        ResponseDadReportListDtoBuilder responseDadReportListDto = ResponseDadReportListDto.builder();

        responseDadReportListDto.key( dadReport.getRequestDate() );
        responseDadReportListDto.date( dadReport.getRequestDate() );
        responseDadReportListDto.dadDetId( dadReport.getDadDetId() );
        responseDadReportListDto.showCount( dadReport.getShowCount() );
        responseDadReportListDto.clickCount( dadReport.getClickCount() );
        responseDadReportListDto.avgShowRank( dadReport.getAvgShowRank() );
        responseDadReportListDto.avgCpc( dadReport.getAvgCpc() );
        responseDadReportListDto.adCost( dadReport.getAdCost() );

        return responseDadReportListDto.build();
    }
}
