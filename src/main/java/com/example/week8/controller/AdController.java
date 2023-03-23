package com.example.week8.controller;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.ad.update.RequestAdActYnAllDto;
import com.example.week8.dto.ad.update.RequestAdUseConfigYnAllDto;
import com.example.week8.repository.AdRepository;
import com.example.week8.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AdController {

    private final AdService adService;

    @GetMapping("/api/ad/find")
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok().body(adService.findAll());
    }

    @PostMapping("/api/ad/save")
    public ResponseEntity<Object> saveAd(@RequestBody AdDto adDto){
        return ResponseEntity.ok().body(adService.saveAd(adDto));
    }

    @PutMapping("/api/ad/update/aduseconfigyn")
    public ResponseEntity<?> updateAdUseConfigYnALl(@RequestBody RequestAdUseConfigYnAllDto requestAdUseConfigYnAllDto){
        System.out.println(requestAdUseConfigYnAllDto.getLongList().size());
        System.out.println(requestAdUseConfigYnAllDto.getYn());
        adService.updateAdUseConfigYnALl(requestAdUseConfigYnAllDto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/api/ad/delete/adactyn")
    public ResponseEntity<?> updateAdActYnALl(@RequestBody RequestAdActYnAllDto requestAdActYnAllDto){
        System.out.println(requestAdActYnAllDto.getLongList().size());
        System.out.println(requestAdActYnAllDto.getYn());
        adService.updateAdActYnALl(requestAdActYnAllDto);
        return ResponseEntity.ok().body(null);
    }
}
