package com.example.week8.controller;

import com.example.week8.dto.AdDto;
import com.example.week8.repository.AdRepository;
import com.example.week8.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AdController {

    private final AdService adService;
    private final AdRepository adRepository;


    @GetMapping("/api/ad/find")
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok().body(adService.findAll());
    }

    @GetMapping("/api/ad/find/manage")
    public ResponseEntity<?> findAdManage(@RequestParam("name") String name,@RequestParam("agroupName") String agroupName){
        return ResponseEntity.ok().body(adRepository.findBySulbin(name,agroupName));
    }


    @PostMapping("/api/ad/save")
    public ResponseEntity<Object> saveAd(@RequestBody AdDto adDto){
        return ResponseEntity.ok().body(adService.saveAd(adDto));
    }


}
