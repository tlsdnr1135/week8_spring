package com.example.week8.controller;

import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.service.TaskRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<?> saveFiles(@RequestParam("formData") MultipartFile multipartFile, String taskName, String adminId) throws IOException {
        taskRequestService.saveFiles(multipartFile, taskName, adminId);
        return ResponseEntity.ok().body(null);
    }

    //파일 다운로드
    @GetMapping(value = "/api/v1/down/files", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<Resource> downFiles(){
        ResponseEntity<Resource> result = null;

        try{
            String originFileName = URLDecoder.decode("Test.csv", "UTF-8");//

            Resource file = new FileSystemResource("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\java\\com\\example\\week8\\file/" + File.separator + originFileName);

            if(!file.exists()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//            String onlyFileName = originFileName.substring(originFileName.lastIndexOf("_") + 1);
            HttpHeaders header = new HttpHeaders();
//            header.add("Content-Disposition", "attachment;);

            result = new ResponseEntity<>(file, header, HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }



}
