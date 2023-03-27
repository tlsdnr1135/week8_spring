package com.example.week8.controller;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.ad.find.AdManageDto;
import com.example.week8.dto.agroup.find.ResponseAgroupDetailsDto;
import com.example.week8.dto.agroup.update.AgroupUpdateAgroupActYnReqDto;
import com.example.week8.dto.agroup.update.AgroupUpdateAgroupNameReqDto;
import com.example.week8.dto.agroup.update.AgroupUpdateAgroupUseActYnReqDto;
import com.example.week8.dto.agroup.update.AgroupUpdateOnOffReqDto;
import com.example.week8.dto.agroup.find.AgroupFindResDto;
import com.example.week8.dto.agroup.save.AgroupSaveReqDto;
import com.example.week8.service.AgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //광고그룹 상세 조회
    @GetMapping("/api/agroup/find/details")
    public ResponseEntity<ResponseAgroupDetailsDto> findDetails(@RequestParam("agroupId") Long agroupId){
        return ResponseEntity.ok().body(agroupService.findDetails(agroupId));
    }

    //그룹리스트
    @GetMapping("/api/agroup/find/manage")
    public ResponseEntity<List<AdManageDto>> findAdManage(@RequestParam("name") String name, @RequestParam("agroupName") String agroupName){
        return ResponseEntity.ok().body(agroupService.findBySulbin(name,agroupName));
    }

    //광고 그룹 등록
    @PostMapping("/api/agroup/save")
    public ResponseEntity<AgroupFindResDto> saveAgroup(@RequestBody AgroupSaveReqDto AgroupSaveReqDto){
        return ResponseEntity.ok().body(agroupService.saveAgroup(AgroupSaveReqDto));
    }

    //On/Off 일괄 변경(agroup_use_act_yn)
    @PutMapping("/api/agroup/update/onoff")
    public ResponseEntity<?> updateAgroupOnOff(@RequestBody AgroupUpdateOnOffReqDto agroupFindOnOffReqDto){
        agroupService.updateOnOff(agroupFindOnOffReqDto);
        return  ResponseEntity.ok().body("업데이트 성공");
    }

    //agroupUseActYn 변경
    @PutMapping("/api/agroup/update/agroupuseactyn")
    public ResponseEntity<?> updateAgroupAgroupUseActYn(@RequestBody AgroupUpdateAgroupUseActYnReqDto agroupUpdateAgroupUseActYnReqDto){
        return  ResponseEntity.ok().body(agroupService.updateAgroupAgroupUseActYn(agroupUpdateAgroupUseActYnReqDto));
    }
    //agroupName 변경
    @PutMapping("/api/agroup/update/agroupname")
    public ResponseEntity<?> updateAgroupAgroupName(@RequestBody AgroupUpdateAgroupNameReqDto agroupUpdateAgroupUseActYnReqDto){
        return  ResponseEntity.ok().body(agroupService.updateAgroupAgroupName(agroupUpdateAgroupUseActYnReqDto));
    }

    
    //광고 그룹 삭제(agroupActYn=0)
    @PutMapping("/api/agroup/delete/agroupactyn")
    public ResponseEntity<?> updateAgroupActYn(@RequestBody AgroupUpdateAgroupActYnReqDto agroupUpdateAgroupActYnReqDto){
        agroupService.updateAgroupActYn(agroupUpdateAgroupActYnReqDto);
        return ResponseEntity.ok().body("광고 그룹 삭제(agroupActYn) 변경 완료");
    }

}
