package com.example.week8.dto.taskrequest.find;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class ResponseTaskRequestDto {

    private Long key; //아이디
    private String taskName; //태스크 명 - 작업 명
    private String taskStatus; //태스크 상태 - REQ

    private String advId; //회원 ID - 작업 요청한 회원 ID
    private String taskPath; //태스크 요청 파일 경로 - 업로드한 파일 경로
    private String taskRequestTime; //태스크 요청 시간 - 작업 요청한 날짜 및 시간

}
