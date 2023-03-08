package com.example.week8.controller;

import com.example.week8.dto.AdDto;
import com.example.week8.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdController {

    private final AdService adService;


    @GetMapping("/api/ad/save")
    public ResponseEntity<?> saveAd(AdDto adDto){
        adService.saveAd(adDto);
        return null;
    }


}
