package com.example.week8.controller;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.agroup.find.AgroupFindOnOffReqDto;
import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.example.week8.dto.agroup.save.AgroupSaveReqDto;
import com.example.week8.entity.Agroup;
import com.example.week8.entity.Item;
import com.example.week8.repository.AgroupRepository;
import com.example.week8.service.AgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AgroupController {

    private final AgroupService agroupService;


    //광고 그룹 가져오기(전체)
    @GetMapping("/api/agroup/find")
    public ResponseEntity<ItemObject> findAgroup(){
        ItemObject itemObject = new ItemObject();
        itemObject.setAgroupFindResDtos(agroupService.findAgroup());
        return ResponseEntity.ok().body(itemObject);
    }

    //On/Off 일괄 변경
    @PutMapping("/api/agroup/find/onoff")
    public ResponseEntity<?> findAgroupOnOff(@RequestBody AgroupFindOnOffReqDto agroupFindOnOffReqDto){
        agroupService.updateOnOff(agroupFindOnOffReqDto);
        return  ResponseEntity.ok().body("업데이트 성공");
    }


    //광고 그룹 등록
    @PostMapping("/api/agroup/save")
    public ResponseEntity<AgroupFindResDto> saveAgroup(@RequestBody AgroupSaveReqDto AgroupSaveReqDto){
        return ResponseEntity.ok().body(agroupService.saveAgroup(AgroupSaveReqDto));
    }

}
