package com.example.week8.controller;

import com.example.week8.dto.daddet.update.RequestDadDetActYnDeleteAllDto;
import com.example.week8.dto.daddet.update.RequestDadDetUseConfigYnAllDto;
import com.example.week8.service.DaddetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DaddetController {

    private final DaddetService daddetService;

    //OnOff 일괄 버튼
    @PutMapping("/api/daddet/update/dadusecofingyn")
    public ResponseEntity<?> updateDadUseConfigYnOnOffAll(@RequestBody RequestDadDetUseConfigYnAllDto requestDadDetUseConfigYnAllDto){
        System.out.println(requestDadDetUseConfigYnAllDto.getLongList().size());
        daddetService.updateDadUseConfigYnOnOffAll(requestDadDetUseConfigYnAllDto);
        return ResponseEntity.ok().body(null);
    }
    
    //Delete 버튼
    @PutMapping("/api/daddet/update/daddetactyn")
    public ResponseEntity<?> updateDaddetActYnDeleteAll(@RequestBody RequestDadDetActYnDeleteAllDto requestDadDetActYnDeleteAllDto){
        System.out.println(requestDadDetActYnDeleteAllDto.getLongList().size());
        daddetService.updateDaddetActYnDeleteAll(requestDadDetActYnDeleteAllDto);
        return ResponseEntity.ok().body(null);
    }
}