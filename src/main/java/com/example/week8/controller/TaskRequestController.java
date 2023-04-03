package com.example.week8.controller;

import com.example.week8.service.TaskRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TaskRequestController {

    private final TaskRequestService taskRequestService;
    //파일 저장
    @PostMapping("/api/v1/files")
    public ResponseEntity<?> saveFiles(@RequestParam("formData") MultipartFile formData) throws IOException {
        System.out.println(formData);
        System.out.println(formData.getOriginalFilename());
        System.out.println(formData.getName());
        System.out.println(formData.getResource());
        formData.transferTo(new File("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\java\\com\\example\\week8\\controller"));
//        System.out.println(formData.getResource());
        taskRequestService.saveFiles();
        return ResponseEntity.ok().body(null);
    }

}
