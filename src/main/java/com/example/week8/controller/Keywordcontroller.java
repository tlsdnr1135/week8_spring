package com.example.week8.controller;

import com.example.week8.dto.kwd.find.KwdFindResDto;
import com.example.week8.dto.kwd.save.KwdSaveReqDto;
import com.example.week8.dto.kwd.save.KwdSaveResDto;
import com.example.week8.entity.Kwd;
import com.example.week8.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Keywordcontroller {

    private final KeywordService keywordService;

    @GetMapping("/api/keyword/find")
    public ResponseEntity<List<KwdFindResDto>> keywordFind(){
        return ResponseEntity.ok().body(keywordService.keywordFind());
    }

    @PostMapping("/api/keyword/save")
    public ResponseEntity<KwdSaveResDto> keywordSave(@RequestBody KwdSaveReqDto kwdSaveReqDto){
        return ResponseEntity.ok().body(keywordService.keywordSave(kwdSaveReqDto));
    }

}
