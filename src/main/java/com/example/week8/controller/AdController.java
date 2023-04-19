package com.example.week8.controller;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.ad.update.RequestAdActYnAllDto;
import com.example.week8.dto.ad.update.RequestAdUseConfigYnAllDto;
import com.example.week8.dto.ad.update.ResponseCurrentStateAdListDto;
import com.example.week8.entity.Ad;
import com.example.week8.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdController {

    private final AdService adService;

    //find 같은 거 빼기.
    //동사x
    @GetMapping("/api/ad/find")
    public ResponseEntity<List<Ad>> findAll(){
        return ResponseEntity.ok().body(adService.findAll());
    }

    //광고 현황 테이블
    @GetMapping("/api/ad/lists/currentstate")
    public ResponseEntity<List<ResponseCurrentStateAdListDto>> findCurrentStateAdLists(){
        return ResponseEntity.ok().body(adService.findCurrentStateAdLists());
    }

    @PostMapping("/api/ad/save")
    public ResponseEntity<String> saveAd(@RequestBody AdDto adDto){
        System.out.println("광고 저장 시작");
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
