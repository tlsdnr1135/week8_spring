package com.example.week8.controller;

import com.example.week8.dto.adv.update.AdvAdIngActYnReqDto;
import com.example.week8.service.AdvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdvController {

    private final AdvService advService;

    //상세조회
    @GetMapping("/api/adv/find")
    public ResponseEntity<?> findAdv(@RequestParam String name){
        return ResponseEntity.ok().body(advService.findAdv(name));
    }

    //광고활성여부 변경
    @PutMapping("/api/adv/update")
    public ResponseEntity<?> updateAdvAdIngActYn(@RequestBody AdvAdIngActYnReqDto advAdIngActYnReqDto){
        return ResponseEntity.ok().body(advService.updateAdvAdIngActYn(advAdIngActYnReqDto));
    }

}
