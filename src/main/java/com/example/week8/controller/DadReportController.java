package com.example.week8.controller;

import com.example.week8.dto.dadreport.find.ResponseDadReportListDto;
import com.example.week8.entity.DadReport;
import com.example.week8.repository.DadReportRepository;
import com.example.week8.service.DadReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DadReportController {

    private final DadReportService dadReportService;

    @GetMapping("/api/v1/lists")
    public ResponseEntity<List<ResponseDadReportListDto>> getListsByDadDetId(@RequestParam(required = true) Long id){
        return ResponseEntity.ok().body(dadReportService.getListsByDadDetId(id));
    }

}
