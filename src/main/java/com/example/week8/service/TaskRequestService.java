package com.example.week8.service;

import com.example.week8.dto.taskrequest.find.ResponseTaskRequestDto;
import com.example.week8.entity.Admin;
import com.example.week8.entity.TaskRequest;
import com.example.week8.mapper.TaskRequestMapper;
import com.example.week8.repository.AdminRepository;
import com.example.week8.repository.TaskRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        String originFilename = multipartFile.getOriginalFilename(); //인사발령문_이요셉_230329.pdf
        System.out.println("원본 파일 이름" + originFilename);
        System.out.println("작업명 이름 " + taskName);

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
                .taskPath("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\java\\com\\example\\week8\\file/")
                .build();

        taskRequestRepository.save(taskRequest);


        multipartFile.transferTo(new File("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\java\\com\\example\\week8\\file/"+originFilename));
    }

    //TaskRequest 리스트 가져오기
    public List<ResponseTaskRequestDto> getTaskRequestLists() {

        List<TaskRequest> taskRequests = taskRequestRepository.findAll();
        System.out.println(taskRequests.get(0).getAdmin().getName());
        return TaskRequestMapper.INSTANCE.toResponseTaskRequestDto(taskRequests);
    }

}
