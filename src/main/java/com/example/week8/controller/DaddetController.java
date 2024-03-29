package com.example.week8.controller;

import com.example.week8.dto.daddet.find.ResponseDadDetListJoinAdKwdItem;
import com.example.week8.dto.daddet.update.RequestConfirmApprovalDto;
import com.example.week8.dto.daddet.update.RequestConfirmRejectDto;
import com.example.week8.dto.daddet.update.RequestDadDetActYnDeleteAllDto;
import com.example.week8.dto.daddet.update.RequestDadDetUseConfigYnAllDto;
import com.example.week8.service.DaddetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DaddetController {

    private final DaddetService daddetService;


    @GetMapping("/api/daddet/lists")
    public ResponseEntity<List<ResponseDadDetListJoinAdKwdItem>> getDaddetListsJoinAdkwdItem(@RequestParam(required = false) String kwdName){
        if(kwdName == null){
            kwdName="";
        }
        return ResponseEntity.ok().body(daddetService.getDaddetListsJoinAdkwdItem(kwdName));
    }

    //OnOff 일괄 버튼
    @PutMapping("/api/daddet/update/dadusecofingyn")
    public ResponseEntity<?> updateDadUseConfigYnOnOffAll(@RequestBody RequestDadDetUseConfigYnAllDto requestDadDetUseConfigYnAllDto){
        System.out.println(requestDadDetUseConfigYnAllDto.getLongList().size());
        daddetService.updateDadUseConfigYnOnOffAll(requestDadDetUseConfigYnAllDto);
        return ResponseEntity.ok().body(null);
    }

    //검수 처리(반려)
    @PutMapping("/api/daddet/confirm/reject")
    public ResponseEntity<?> updateConfirmReject(@RequestBody RequestConfirmRejectDto RequestConfirmRejectDto){
        return ResponseEntity.ok().body(daddetService.updateConfirmReject(RequestConfirmRejectDto));
    }

    //검수 처리(승인)
    @PutMapping("/api/daddet/confirm/approval")
    public ResponseEntity<?> updateConfirmApproval(@RequestBody RequestConfirmApprovalDto requestConfirmApprovalDto){
        return ResponseEntity.ok().body(daddetService.updateConfirmApproval(requestConfirmApprovalDto));
    }


    
    //Delete 버튼
    @PutMapping("/api/daddet/update/daddetactyn")
    public ResponseEntity<?> updateDaddetActYnDeleteAll(@RequestBody RequestDadDetActYnDeleteAllDto requestDadDetActYnDeleteAllDto){
        System.out.println(requestDadDetActYnDeleteAllDto.getLongList().size());
        daddetService.updateDaddetActYnDeleteAll(requestDadDetActYnDeleteAllDto);
        return ResponseEntity.ok().body(null);
    }

}
