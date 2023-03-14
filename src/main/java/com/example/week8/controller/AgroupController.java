package com.example.week8.controller;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.example.week8.dto.agroup.save.AgroupSaveReqDto;
import com.example.week8.entity.Agroup;
import com.example.week8.entity.Item;
import com.example.week8.repository.AgroupRepository;
import com.example.week8.service.AgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AgroupController {

    private final AgroupService agroupService;

    //광고 그룹 가져오기
    @GetMapping("/api/agroup/find")
    public ResponseEntity<ItemObject> findAgroup(){
        ItemObject itemObject = new ItemObject();
        itemObject.setAgroupFindResDtos(agroupService.findAgroup());
        return ResponseEntity.ok().body(itemObject);
    }

    //광고 그룹 등록
    @PostMapping("/api/agroup/save")
    public ResponseEntity<AgroupFindResDto> saveAgroup(@RequestBody AgroupSaveReqDto AgroupSaveReqDto){
        return ResponseEntity.ok().body(agroupService.saveAgroup(AgroupSaveReqDto));
    }

}
