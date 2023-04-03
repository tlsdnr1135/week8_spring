package com.example.week8.controller;

import com.example.week8.service.TaskRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class TaskRequestController {

    private final TaskRequestService taskRequestService;
    //파일 저장
    @PostMapping("/api/v1/files")
    public ResponseEntity<?> saveFiles(@RequestBody MultipartFile formData){
        System.out.println(formData);
        taskRequestService.saveFiles();
        return ResponseEntity.ok().body(null);
    }

}
