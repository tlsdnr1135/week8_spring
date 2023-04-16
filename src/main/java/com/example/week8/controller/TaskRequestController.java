package com.example.week8.controller;

import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.service.TaskRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskRequestController {

    private final TaskRequestService taskRequestService;


    //TaskRequest 리스트 가져오기
    @GetMapping("/api/v1/task-request/lists")
    public ResponseEntity<List<ResponseTaskRequestDto>> getTaskRequestLists() {
        return ResponseEntity.ok().body(taskRequestService.getTaskRequestLists());
    }

    //파일 저장
    @PostMapping("/api/v1/files")
    public ResponseEntity<?> saveFiles(@RequestParam("formData") MultipartFile multipartFile, String taskName, String adminId, HttpServletRequest request) throws IOException {
        System.out.println("-------------------파일 저장------------------------");
        taskRequestService.saveFiles(multipartFile, taskName, adminId);
        return ResponseEntity.ok().body("파일 저장!");
    }

    //파일 다운로드
    @GetMapping(value = "/api/v1/down/files")
    public ResponseEntity<Resource> downFiles(@RequestParam(value="fileName", required = true) String fileName) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/csv; charset=UTF-8");

        return ResponseEntity.ok().headers(httpHeaders).body(taskRequestService.downFiles(fileName));
    }
}
