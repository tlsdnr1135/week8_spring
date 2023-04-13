package com.example.week8.service;

import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.entity.Admin;
import com.example.week8.entity.TaskRequest;
import com.example.week8.mapper.TaskRequestMapper;
import com.example.week8.repository.AdminRepository;
import com.example.week8.repository.TaskRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskRequestService {

    private final TaskRequestRepository taskRequestRepository;
    private final AdminRepository adminRepository;

    public void saveFiles(MultipartFile multipartFile, String taskName, String adminId) throws IOException {
        String originFilename = multipartFile.getContentType(); //인사발령문_이요셉_230329.pdf
        String[] arr = originFilename.split("/");
        System.out.println("원본 파일 이름" + originFilename);
        System.out.println("작업명 이름 " + taskName);
        System.out.println("스플릿 " + arr[1]);

        System.out.println("이건 어캐나올까");

        InputStream inputStream = multipartFile.getInputStream();
        System.out.println(inputStream);

        //날짜
        String parsedLocalDateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("날짜입니당"+parsedLocalDateTimeNow);

        //프록시 객체
        Admin admin = adminRepository.getReferenceById(adminId);

        //엔티티 만들기
        TaskRequest taskRequest = TaskRequest.builder()
                .admin(admin)
                .requestDate(parsedLocalDateTimeNow)
                .taskStatus("REQ")
                .taskName(taskName)
//                .taskPath("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/") //집
                .taskPath("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/") //회사
                .build();

        taskRequestRepository.save(taskRequest);
        OutputStream outputStream = new FileOutputStream(inputStream.toString());

        multipartFile.transferTo(new File("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/"+taskName+"."+arr[1])); //회사
//        inputStream.transferTo(outputStream); //회사
//        multipartFile.transferTo(new File("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/"+taskName+"."+arr[1])); //집
    }

    //TaskRequest 리스트 가져오기
    public List<ResponseTaskRequestDto> getTaskRequestLists()  {

        List<TaskRequest> taskRequests = taskRequestRepository.findAll();
        return TaskRequestMapper.INSTANCE.toResponseTaskRequestDto(taskRequests);
    }

    //파일 다운로드
    public Resource downFiles(String fileName) throws MalformedURLException {
        System.out.println("파일 다운로드");
        String originFileName = null;
        if(fileName == null){
            throw new RuntimeException("file 이름이 없어요ㅜㅜㅜ");
        }

        UrlResource urlResource;
        try {
            originFileName = URLDecoder.decode(fileName + ".csv", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("오리지널 파일 네임 " + originFileName);
        return new UrlResource("file:\\" + "C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/" + originFileName); //회사
//        return new UrlResource("file:\\" + "C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/" + originFileName); //집
    }


}
