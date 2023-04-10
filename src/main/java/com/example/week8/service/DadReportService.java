package com.example.week8.service;

import com.example.week8.dto.dadreport.find.ResponseDadReportListDto;
import com.example.week8.entity.DadReport;
import com.example.week8.mapper.DadReportMapper;
import com.example.week8.repository.DadReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadReportService {
    private final DadReportRepository dadReportRepository;
    public List<ResponseDadReportListDto> getListsByDadDetId(Long id) {
        List<ResponseDadReportListDto> responseDadReportListDtos = DadReportMapper.INSTANCE.toResponseDadReportListDto(dadReportRepository.findByDadDetId(id));
        return responseDadReportListDtos;
    }
}
