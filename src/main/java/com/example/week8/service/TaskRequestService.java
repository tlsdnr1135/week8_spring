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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskRequestService {

    private final TaskRequestRepository taskRequestRepository;
    private final AdminRepository adminRepository;

    //파일 저장
    public void saveFiles(MultipartFile multipartFile, String taskName, String adminId) throws IOException {
        String originFilename = multipartFile.getContentType();
        String[] arr = originFilename.split("/");
        System.out.println("원본 파일 이름" + originFilename);
        System.out.println("작업명 이름 " + taskName);
        System.out.println("스플릿 " + arr[1]);

        //날짜
        String parsedLocalDateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("날짜입니당"+parsedLocalDateTimeNow);

        //프록시 객체
        Admin admin = adminRepository.getReferenceById(adminId);

        //uuid만들기
        String[] uuids = UUID.randomUUID().toString().split("-");
        String saveFileName = uuids[0] + "-" + taskName;
        System.out.println("uuid : " + saveFileName);

        //엔티티 만들기
        TaskRequest taskRequest = TaskRequest.builder()
                .admin(admin)
                .requestDate(parsedLocalDateTimeNow)
                .taskStatus("REQ")
                .taskName(taskName)
//                .taskPath("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/") //집
                .taskPath("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/"+saveFileName+"."+arr[1]) //회사
                .build();

        taskRequestRepository.save(taskRequest);

        try {
            String line;
            Charset charset = StandardCharsets.UTF_8;
            BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "x-windows-949"));
            BufferedWriter fw = new BufferedWriter(new FileWriter( "C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/"+saveFileName+"."+arr[1],charset)); //회사
//            BufferedWriter fw = new BufferedWriter(new FileWriter( "C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/"+taskName+"."+arr[1],charset)); //회사

            while((line=br.readLine()) != null) {
                System.out.println(line);
                fw.write(line);
                fw.write("\n");
            }
            fw.flush();
            fw.close();
            br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
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

        try {
            originFileName = URLDecoder.decode(fileName + ".csv", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        //파일명으로 패스 찾고...
        TaskRequest taskRequest = taskRequestRepository.findByTaskName(fileName);
        System.out.println("파일 패스 : " + taskRequest.getTaskPath());

        System.out.println("오리지널 파일 네임 " + originFileName);
        return new UrlResource("file:\\" + taskRequest.getTaskPath()); //회사
//        return new UrlResource("file:\\" + "C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/" + originFileName); //집
    }


}
