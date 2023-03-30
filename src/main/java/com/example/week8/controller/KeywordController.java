package com.example.week8.controller;

import com.example.week8.dto.kwd.find.KwdFindResDto;
import com.example.week8.dto.kwd.find.RequestKwdNameUpdateManualDto;
import com.example.week8.dto.kwd.find.RequestManualOffDto;
import com.example.week8.dto.kwd.find.ResponseKeywordListManualCnrKwdYn;
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

    //키워드 리스트(키워드 검수, admin, ManualCnrKwdYn)
    @GetMapping("/api/keyword/find/manual")
    public ResponseEntity<List<ResponseKeywordListManualCnrKwdYn>> keywordListsManualCnrKwdYnFind(@RequestParam(required = false) String keywordName){
        if(keywordName == null){
            keywordName="";
        }
        return ResponseEntity.ok().body(keywordService.keywordListsManualCnrKwdYnFind(keywordName));
    }

    //검수 대상 키워드 등록(키워드 검수, admin, ManualCnrKwdYn)
    @PutMapping("/api/keyword/update/manual")
    public ResponseEntity<?> keywordListsManualCnrKwdYnUpdate(@RequestBody RequestKwdNameUpdateManualDto requestKwdNameUpdateManualDto){
        System.out.println("키워드 DTO : "+ requestKwdNameUpdateManualDto.getKeywordName() );
        return ResponseEntity.ok().body(keywordService.keywordListsManualCnrKwdYnUpdate(requestKwdNameUpdateManualDto.getKeywordName()));
    }

    //검수 대상 키워드 삭제(키워드 검수, admin, ManualCnrKwdYn)
    @PutMapping("/api/keyword/manual/off")
    public ResponseEntity<String> keywordListsManualCnrKwdYnOffUpdate(@RequestBody RequestManualOffDto requestManualOffDto){
        return ResponseEntity.ok().body(keywordService.keywordListsManualCnrKwdYnOffUpdate(requestManualOffDto.getId()));
    }


    @PostMapping("/api/keyword/save")
    public ResponseEntity<KwdSaveResDto> keywordSave(@RequestBody KwdSaveReqDto kwdSaveReqDto){
        return ResponseEntity.ok().body(keywordService.keywordSave(kwdSaveReqDto));
    }
}
