package com.example.week8.controller;

import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.service.TaskRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
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
    public ResponseEntity<?> saveFiles(@RequestParam("formData") MultipartFile multipartFile, String taskName, String adminId, HttpServletRequest request) throws IOException {
        System.out.println("-------------------파일 저장------------------------");
        System.out.println(request.getContentType());
        System.out.println(request.getCharacterEncoding());
        System.out.println(multipartFile.getContentType());
        taskRequestService.saveFiles(multipartFile, taskName, adminId);
        return ResponseEntity.ok().body(null);
    }

    //파일 다운로드
    @GetMapping(value = "/api/v1/down/files")
    public ResponseEntity<?> downFiles(@RequestParam(value="fileName", required = true) String fileName, HttpServletResponse response) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/csv; charset=UTF-8");
        httpHeaders.add("Content-Disposition", "attachment; filename="+fileName+".csv");
//        C:\Users\tlsdn\OneDrive\바탕 화면\4-1
////        File file = new File("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/"+fileName+".csv");
//        File file = new File("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/"+fileName+".csv");
//        File file = new File("C:\\Users\\tlsdn\\OneDrive\\바탕 화면\\4-1/"+fileName+".csv");
//        try {
//            // 파일 응답 스트림 설정
//            OutputStream out = response.getOutputStream();
//            FileInputStream fileInputStream = new FileInputStream(file); // 파일 읽어오기
//
//            int read = 0;
//            byte[] buffer = new byte[1024];
//            // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
////            out.write(new byte[]{(byte) 239, (byte) 187, (byte) 191});
//            while ((read = fileInputStream.read(buffer)) != -1) {
//                System.out.println("읽는중!!!!");
//                out.write(buffer, 0, read);
//            }
//
//        } catch (Exception e) {
//            throw new Exception("download error");
//        }

//        return ResponseEntity.ok().headers(httpHeaders).body(taskRequestService.downFiles(fileName));
        return ResponseEntity.ok().headers(httpHeaders).body(new UrlResource("file:\\" +"C:\\Users\\tlsdn\\OneDrive\\바탕 화면\\4-1/"+fileName+".csv"));
    }
}
