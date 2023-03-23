package com.example.week8.controller;

import com.example.week8.dto.daddet.update.RequestDadDetUseConfigYnAllDto;
import com.example.week8.service.DaddetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DaddetController {

    private final DaddetService daddetService;

    @PutMapping("/api/daddet/update/dadusecofingyn")
    public ResponseEntity<?> updateDadUseConfigYnOnOffAll(RequestDadDetUseConfigYnAllDto requestDadDetUseConfigYnAllDto){
        daddetService.updateDadUseConfigYnOnOffAll(requestDadDetUseConfigYnAllDto);
        return ResponseEntity.ok().body(null);
    }
}
