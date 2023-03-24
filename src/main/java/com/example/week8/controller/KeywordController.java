package com.example.week8.controller;

import com.example.week8.dto.kwd.find.KwdFindResDto;
import com.example.week8.dto.kwd.save.KwdSaveReqDto;
import com.example.week8.dto.kwd.save.KwdSaveResDto;
import com.example.week8.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/api/keyword/find")
    public ResponseEntity<List<KwdFindResDto>> keywordFind(){
        return ResponseEntity.ok().body(keywordService.keywordFind());
    }

    //키워드 리스트
    @GetMapping("/api/keyword/find/joindaddet")
    public ResponseEntity<?> keywordListJoinDadDetFind(@RequestParam(required = false) Integer adId, String kwdName){
        System.out.println("진입");
        System.out.println(adId);
        if(kwdName==null){
            kwdName="";
        }
        return ResponseEntity.ok().body(keywordService.keywordListJoinDadDetFind(adId,kwdName));
    }


    @PostMapping("/api/keyword/save")
    public ResponseEntity<KwdSaveResDto> keywordSave(@RequestBody KwdSaveReqDto kwdSaveReqDto){
        return ResponseEntity.ok().body(keywordService.keywordSave(kwdSaveReqDto));
    }
}
