package com.example.week8.controller;

import com.example.week8.dto.AdDto;
import com.example.week8.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
//        System.out.println("adController agroup.getAgroupName()= " +  adDto.getAgroup().getAgroupName());
//        System.out.println("adController agroup.getName() = " +  adDto.getAdv().getName());
//        System.out.println("adController kwd. = " +  adDto.getKwd().get(0).getKwdName());
//        System.out.println("adController kwd. = " +  adDto.getKwd().get(1).getKwdName());
        return ResponseEntity.ok().body(adService.saveAd(adDto));
    }


}
